package com.on.spring.service;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshExp;

    @Override
    public TokenResponse login(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(User::getEmail)
                .map(email -> {
                    String refreshToken = jwtTokenProvider.generateRefreshToken(email);
                    return new RefreshToken(email, refreshToken, refreshExp);
                })
                .map(refreshTokenRepository::save)
                .map(refreshToken -> {
                    String accessToken = jwtTokenProvider.generateAccessToken(refreshToken.getEmail());
                    return new TokenResponse(accessToken, refreshToken.getRefreshToken(), refreshToken.getRefreshExp());
                })
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public AccessTokenResponse tokenRefresh(String receivedToken) {
        if (!jwtTokenProvider.isRefreshToken(receivedToken)) {
            throw new InvalidTokenException();
        }

        return refreshTokenRepository.findByRefreshToken(receivedToken)
                .map(refreshToken -> {
                    String generateRefreshToken = jwtTokenProvider.generateRefreshToken(refreshToken.getEmail());
                    return refreshToken.update(generateRefreshToken, refreshExp);
                })
                .map(refreshTokenRepository::save)
                .map(refreshToken -> new AccessTokenResponse(jwtTokenProvider.generateAccessToken(refreshToken.getEmail())))
                .orElseThrow(UserNotFoundException::new);
    }
}