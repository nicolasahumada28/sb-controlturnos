package com.timmynet.authorization.auth_service.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.timmynet.authorization.auth_service.model.entity.ApiKey;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    Optional<ApiKey> findByKeyAndActiveTrue(String key);
}
