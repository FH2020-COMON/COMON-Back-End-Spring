package com.on.spring.payload.response;

import lombok.Getter;

@Getter
public class TokenResponse {
    private String accessToken;
    private String expireTime;
}
