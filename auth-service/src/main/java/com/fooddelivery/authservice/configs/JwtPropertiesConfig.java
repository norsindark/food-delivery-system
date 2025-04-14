package com.fooddelivery.authservice.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtPropertiesConfig {
    private String secret;
    private long accessTokenExpirationMs;
    private long refreshTokenExpirationMs;
}
