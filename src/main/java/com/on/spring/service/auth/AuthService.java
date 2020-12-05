package com.on.spring.service.auth;

import com.on.spring.payload.request.LoginRequest;

public interface AuthService {
    public String login(LoginRequest request);
}
