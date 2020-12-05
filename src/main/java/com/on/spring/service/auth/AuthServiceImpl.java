package com.on.spring.service.auth;

import com.on.spring.entity.refreshtoken.RefreshToken;
import com.on.spring.entity.refreshtoken.RefreshTokenRepository;
import com.on.spring.entity.user.User;
import com.on.spring.entity.user.UserRepository;
import com.on.spring.exception.InvalidTokenException;
import com.on.spring.exception.UserNotFoundException;
import com.on.spring.payload.request.LoginRequest;
import com.on.spring.payload.response.AccessTokenResponse;
import com.on.spring.payload.response.TokenResponse;
import com.on.spring.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponse login(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(User::getEmail)
                .map(email ->{
                    String accessToken = jwtTokenProvider.generateAccessToken(email);
                    return new TokenResponse(accessToken);
                })
                .orElseThrow(UserNotFoundException::new);
    }
}