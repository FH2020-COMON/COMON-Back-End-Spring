package com.on.spring.service.auth;

import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.payload.request.LoginRequest;
import com.on.spring.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .map(user -> {
                    System.out.println(user.getEmail());
                    return user;
                })
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                    .map(user -> jwtTokenProvider.generateAccessToken(user.getEmail()))
                .orElseThrow(UserNotFoundException::new);
    }
}