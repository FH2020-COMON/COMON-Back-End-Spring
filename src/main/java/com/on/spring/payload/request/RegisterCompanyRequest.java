package com.on.spring.payload.request;

public class RegisterCompanyRequest {
    private String name;
    private String address;

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }
}