package com.on.spring.payload.request;

import lombok.Getter;

import java.util.List;

@Getter
public class RegisterCompanyRequest {
    private String companyName;
    private String companyAddress;
    private String companyNumber;
    private String ceoName;

    private String userEmail;
}