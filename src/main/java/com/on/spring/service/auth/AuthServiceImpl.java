package com.on.spring.service.auth;

import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.payload.request.LoginRequest;
import com.on.spring.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginRequest request) {
        System.out.println(userRepository.findByEmail(request.getEmail()).get().getEmail());
        return userRepository.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(User::getEmail)
                .map(jwtTokenProvider::generateAccessToken)
                .orElseThrow(UserNotFoundException::new);
    }
}