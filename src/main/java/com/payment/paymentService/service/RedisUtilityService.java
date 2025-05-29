package com.payment.paymentService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class RedisUtilityService {
     private final StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value, Duration ttl) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        if (ttl != null) {
            ops.set(key, value, ttl);
        } else {
            ops.set(key, value);
        }
    }

    /**
     * Get value
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
