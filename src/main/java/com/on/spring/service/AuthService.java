package com.on.spring.service;

import com.on.spring.payload.request.LoginRequest;
import com.on.spring.payload.response.TokenResponse;

public interface AuthService {
    public void login(LoginRequest request);
    public TokenResponse tokenRefresh(String refreshToken);
}
