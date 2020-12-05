package com.on.spring.controller;

import com.on.spring.payload.request.LoginRequest;
import com.on.spring.security.JwtTokenProvider;
import com.on.spring.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public String signIn(@RequestBody @Valid LoginRequest request) {
        System.out.println("email : " + request.getEmail() + "password : " + request.getPassword());
        return authService.login(request);
    }

    @GetMapping("/")
    public String getUserEmail(String accessToken) {
        System.out.println("Email : " + jwtTokenProvider.getEmail(accessToken));
        return jwtTokenProvider.getEmail(accessToken);
    }
}