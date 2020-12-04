package com.on.spring.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter @AllArgsConstructor @NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String password;

    @Email
    private String email;
}
