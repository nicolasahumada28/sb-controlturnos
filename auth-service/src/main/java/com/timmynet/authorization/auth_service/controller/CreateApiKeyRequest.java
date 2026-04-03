package com.timmynet.authorization.auth_service.controller;

public class CreateApiKeyRequest {

    private String owner;

    public CreateApiKeyRequest() {
    }

    public CreateApiKeyRequest(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
