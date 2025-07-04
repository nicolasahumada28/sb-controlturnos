package com.timmynet.controlturnos.sb_controlturnos.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.timmynet.controlturnos.sb_controlturnos.model.entity.ApiKey;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    Optional<ApiKey> findByKeyAndActiveTrue(String key);

}
