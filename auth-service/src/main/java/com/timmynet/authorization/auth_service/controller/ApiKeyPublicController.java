package com.timmynet.authorization.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.timmynet.authorization.auth_service.service.ApiKeyService;
import com.timmynet.authorization.auth_service.model.entity.ApiKey;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class ApiKeyPublicController {

    @Autowired
    private ApiKeyService service;

    // Validar API Key
    @GetMapping("/validate")
    public ResponseEntity<?> validateApiKey(@RequestParam String key) {
        Optional<ApiKey> apiKey = service.validaApiKey(key);

        if (apiKey.isPresent()) {
            return ResponseEntity.ok(apiKey.get());
        } else {
            return ResponseEntity.status(401).body("API Key inválida o revocada");
        }
    }
}
