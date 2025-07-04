package com.timmynet.controlturnos.sb_controlturnos.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timmynet.controlturnos.sb_controlturnos.model.entity.ApiKey;
import com.timmynet.controlturnos.sb_controlturnos.repository.ApiKeyRepository;

@Service
public class ApiKeyServiceImpl implements ApiKeyService{

    @Autowired
    private ApiKeyRepository repository;

    @Override
    public ApiKey generaApiKey(String owner) {
        String clave = generaClaveSegura();
        ApiKey apiKey = new ApiKey();
        apiKey.setKey(clave);
        apiKey.setOwner(owner);
        apiKey.setCreatedAt(java.time.LocalDateTime.now());
        apiKey.setActive(true);
        return repository.save(apiKey);        
    }

    @Override
    public boolean validaApiKey(String key) {
        return repository.findByKeyAndActiveTrue(key).isPresent();
    }

    @Override
    public void revocaApiKey(String key) {
        Optional<ApiKey> optional = repository.findByKeyAndActiveTrue(key);
        optional.ifPresent(apikey -> {
            apikey.setActive(false);
            repository.save(apikey);
        });
    }

    @Override
    public String generaClaveSegura() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32]; // 256 bits
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
