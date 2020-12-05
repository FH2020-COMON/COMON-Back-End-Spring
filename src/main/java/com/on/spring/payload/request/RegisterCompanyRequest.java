package com.on.spring.payload.request;

import javax.validation.constraints.NotNull;

public class RegisterCompanyRequest {

    @NotNull
    private String name;
    @NotNull
    private String address;

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }
}