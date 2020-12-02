package com.on.spring.payload.request;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class LoginRequest {
    @Email
    private String email;

    private String password;
}
