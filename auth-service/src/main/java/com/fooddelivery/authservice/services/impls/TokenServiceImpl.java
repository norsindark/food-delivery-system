package com.fooddelivery.authservice.services.impls;

import com.fooddelivery.authservice.configs.JwtPropertiesConfig;
import com.fooddelivery.authservice.services.interfaces.RedisService;
import com.fooddelivery.authservice.services.interfaces.TokenService;
import com.fooddelivery.authservice.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final RedisService redisService;
    private final JwtPropertiesConfig jwtPropertiesConfig;
    private static final String REFRESH_TOKEN_KEY_PREFIX = "refreshToken:";
    private final JwtUtil jwtUtil;

    @Override
    public void storeRefreshTokenToRedis(String identifier, String refreshToken) {
        String key = REFRESH_TOKEN_KEY_PREFIX + identifier;

        long expirationMs = jwtPropertiesConfig.getRefreshTokenExpirationMs();
        long expirationMsToDay = TimeUnit.MILLISECONDS.toDays(expirationMs);

        redisService.save(key, refreshToken, expirationMsToDay, TimeUnit.DAYS);
    }

    @Override
    public String generateAccessToken(String email, String role) {
        return jwtUtil.generateAccessToken(email, role);
    }

    @Override
    public String generateRefreshToken(String email) {
        return jwtUtil.generateRefreshToken(email);
    }
}
