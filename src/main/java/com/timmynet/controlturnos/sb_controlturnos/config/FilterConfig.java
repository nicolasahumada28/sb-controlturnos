package com.timmynet.controlturnos.sb_controlturnos.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.timmynet.controlturnos.sb_controlturnos.util.ApiKeyDbFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ApiKeyDbFilter> apiKeyFilter(ApiKeyDbFilter filter) {
        FilterRegistrationBean<ApiKeyDbFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/api/v1/*"); // Ajusta el patrón según tus necesidades
        return registrationBean;
    }
}
