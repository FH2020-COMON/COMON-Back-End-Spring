package com.on.spring.controller;

import com.on.spring.payload.request.LoginRequest;
import com.on.spring.security.JwtTokenProvider;
import com.on.spring.security.auth.AuthenticationFacade;
import com.on.spring.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationFacade authenticationFacade;

    @PostMapping
    public String signIn(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping
    public String getUserEmail(@RequestHeader("Authorization") String accessToken) {
        String email = authenticationFacade.getUserEmail();
        System.out.println("Email : " + email);
        return email;
    }
}