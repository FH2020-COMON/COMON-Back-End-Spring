package com.on.spring.payload.request;

import lombok.Getter;

@Getter
public class RegisterCompanyRequest {
    private String companyName;
    private String companyAddress;
}