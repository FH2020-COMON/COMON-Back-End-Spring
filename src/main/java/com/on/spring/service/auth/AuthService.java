package com.on.spring.service.auth;

import com.on.spring.payload.request.LoginRequest;
import com.on.spring.payload.response.AccessTokenResponse;
import com.on.spring.payload.response.TokenResponse;

public interface AuthService {
    public TokenResponse login(LoginRequest request);
}
