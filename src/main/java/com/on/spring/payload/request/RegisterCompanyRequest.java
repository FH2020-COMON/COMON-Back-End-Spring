package com.on.spring.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class RegisterCompanyRequest {
    private String name;
    private String address;
}