package com.timmynet.authorization.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timmynet.authorization.auth_service.model.entity.ApiKey;
import com.timmynet.authorization.auth_service.repository.ApiKeyRepository;
import com.timmynet.authorization.auth_service.service.ApiKeyService;

@RestController
@RequestMapping("/api/v1/admin/apikeys")
public class ApiKeyAdminController {

    @Autowired
    private ApiKeyService service;

    @Autowired
    private ApiKeyRepository repository;

    @PostMapping("/revocar")
    public ResponseEntity<?> revocarApiKey(@RequestParam String key) {
        service.revocaApiKey(key);
        return ResponseEntity.ok("API Key revocada correctamente");
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearApiKey() {
        String nuevaClave = service.generaClaveSegura();
        ApiKey key = new ApiKey();
        key.setKey(nuevaClave);
        key.setActive(true);
        repository.save(key);
        // Aquí deberías guardar la nueva clave en la base de datos
        // repository.save(key); // Descomenta y ajusta según tu implementación
        return ResponseEntity.ok(nuevaClave);
    }
}
