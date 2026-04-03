package com.timmynet.authorization.auth_service.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timmynet.authorization.auth_service.model.entity.ApiKey;
import com.timmynet.authorization.auth_service.repository.ApiKeyRepository;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    @Autowired
    private ApiKeyRepository repository;

    @Override
    public Optional<ApiKey> validaApiKey(String key) {
        return repository.findByKeyAndActiveTrue(key);
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
