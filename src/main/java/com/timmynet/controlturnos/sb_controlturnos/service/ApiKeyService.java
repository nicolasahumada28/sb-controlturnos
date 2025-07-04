package com.timmynet.controlturnos.sb_controlturnos.service;

import com.timmynet.controlturnos.sb_controlturnos.model.entity.ApiKey;

public interface ApiKeyService {

    public ApiKey generaApiKey(String owner);
    public boolean validaApiKey(String key);
    public void revocaApiKey(String key);
    public String generaClaveSegura();
}
