package com.fooddelivery.authservice.services.interfaces;

public interface TokenService {
    void storeRefreshTokenToRedis(String identifier, String refreshToken);

    String generateAccessToken(String email, String role);

    String generateRefreshToken(String email);
}
