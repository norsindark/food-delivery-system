package com.fooddelivery.authservice.services.impls.oauth;

import com.fooddelivery.authservice.enums.OAuthProvider;
import com.fooddelivery.authservice.exceptions.InvalidOAuthTokenException;
import com.fooddelivery.authservice.dtos.OAuthUserInfo;
import com.fooddelivery.authservice.services.interfaces.oauth.OAuthStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GoogleOAuthStrategy implements OAuthStrategy {
    private final RestTemplate restTemplate;

    @Override
    public boolean support(OAuthProvider provider) {
        return provider == OAuthProvider.GOOGLE;
    }

    @Override
    public OAuthUserInfo getUserInfo(String idToken) {
        String url = "http://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
        OAuthUserInfo googleInfo = restTemplate.getForObject(url, OAuthUserInfo.class);
        if (googleInfo == null || googleInfo.getEmail() == null || googleInfo.getName() == null)
            throw new InvalidOAuthTokenException("Invalid or empty Google token!");
        return OAuthUserInfo.builder()
                .id(googleInfo.getId())
                .name(googleInfo.getName())
                .email(googleInfo.getEmail())
                .avatar(googleInfo.getAvatar())
                .build();
    }
}
