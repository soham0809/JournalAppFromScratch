package com.journalingapp.soham.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void redisTestFunction() {
        redisTemplate.opsForValue().set("email", "sohamj69@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        System.out.println(email);
    }

}
