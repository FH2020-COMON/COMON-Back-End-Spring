package com.on.spring.service;

import com.on.spring.entity.user.UserRepository;
import com.on.spring.payload.request.LoginRequest;
import com.on.spring.payload.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void login(LoginRequest request) {
        userRepository.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> {

                })

    }

    @Override
    public TokenResponse tokenRefresh(String refreshToken) {
        return null;
    }
}
