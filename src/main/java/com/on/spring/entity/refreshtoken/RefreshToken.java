package com.on.spring.entity.refreshtoken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash(value = "refresh_token")
@NoArgsConstructor @AllArgsConstructor @Getter
@Builder
public class RefreshToken implements Serializable {
    @Id
    private String email;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long refreshExp;

    public RefreshToken update(String refreshToken, Long refreshExp) {
        this.refreshToken = refreshToken;
        this.refreshExp = refreshExp;
        return this;
    }
}
