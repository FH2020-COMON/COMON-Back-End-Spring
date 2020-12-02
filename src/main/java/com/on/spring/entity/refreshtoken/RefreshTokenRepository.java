package com.on.spring.entity.refreshtoken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    public Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
