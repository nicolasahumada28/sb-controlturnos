package com.timmynet.authorization.auth_service.service;

import com.timmynet.authorization.auth_service.model.entity.ApiKey;
import java.util.Optional;

public interface ApiKeyService {
    Optional<ApiKey> validaApiKey(String key);
    void revocaApiKey(String key);
    String generaClaveSegura();
}
