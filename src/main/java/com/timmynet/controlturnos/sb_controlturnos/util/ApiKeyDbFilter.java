package com.timmynet.controlturnos.sb_controlturnos.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.timmynet.controlturnos.sb_controlturnos.service.ApiKeyService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyDbFilter extends OncePerRequestFilter {

    @Autowired
    private ApiKeyService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerKey = request.getHeader("X-API-KEY");
        if(headerKey != null & service.validaApiKey(headerKey)){
            filterChain.doFilter(request, response);
        } else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("API Key no válida o no proporcionada");
        }
    }
}
