package com.on.spring.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class TokenResponse {
    private final String accessToken;
}
