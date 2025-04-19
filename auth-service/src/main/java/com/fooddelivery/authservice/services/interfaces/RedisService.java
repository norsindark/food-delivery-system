package com.fooddelivery.authservice.services.interfaces;

import java.util.concurrent.TimeUnit;

public interface RedisService {
    void save(String key, String value, long duration, TimeUnit unit);
    String get(String key);
    void delete(String key);
}
