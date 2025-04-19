package com.fooddelivery.authservice.services.impls;

import com.fooddelivery.authservice.configs.JwtPropertiesConfig;
import com.fooddelivery.authservice.services.interfaces.RedisService;
import com.fooddelivery.authservice.services.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final RedisService redisService;
    private final JwtPropertiesConfig jwtPropertiesConfig;

    @Override
    public void storeRefreshTokenToRedis(String identifier, String refreshToken) {
        String key = "refreshToken:" + identifier;

        long expirationMs = jwtPropertiesConfig.getRefreshTokenExpirationMs();
        long expirationMsToDay = TimeUnit.MILLISECONDS.toDays(expirationMs);

        redisService.save(key, refreshToken, expirationMsToDay, TimeUnit.DAYS);
    }
}
