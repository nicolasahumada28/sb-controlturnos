package com.timmynet.controlturnos.sb_controlturnos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timmynet.controlturnos.sb_controlturnos.service.ApiKeyService;

@RestController
@RequestMapping("api/admin/apikeys")
public class ApiKeyAdminController {

    @Autowired
    private ApiKeyService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crearApiKey(@RequestParam String owner) {
        return ResponseEntity.ok(service.generaApiKey(owner));
    }

    @PostMapping("/revocar")
    public ResponseEntity<?> revocarApiKey(@RequestParam String key) {
        service.revocaApiKey(key);
        return ResponseEntity.ok("API Key revocada correctamente");
    }
}
