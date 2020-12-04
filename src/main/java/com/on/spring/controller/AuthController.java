package com.on.spring.controller;

import com.on.spring.payload.request.LoginRequest;
import com.on.spring.payload.response.AccessTokenResponse;
import com.on.spring.payload.response.TokenResponse;
import com.on.spring.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public TokenResponse signIn(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @PutMapping
    public AccessTokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.tokenRefresh(refreshToken);
    }
}
