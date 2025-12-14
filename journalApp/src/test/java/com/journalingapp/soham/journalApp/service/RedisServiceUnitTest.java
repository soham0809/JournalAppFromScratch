package com.journalingapp.soham.journalApp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RedisServiceUnitTest {

    @Mock
    private RedisTemplate redisTemplate;

    @Mock
    private ValueOperations valueOperations;

    @InjectMocks
    private RedisService redisService;

    @Test
    public void testSetStoresJson() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        Map<String, String> testMap = new HashMap<>();
        testMap.put("testKey", "testValue");

        // Expected JSON: {"testKey":"testValue"}
        // Actual toString(): {testKey=testValue} (default map toString)

        redisService.set("key", testMap, 100L);

        // This verification expects the serialized JSON
        // It will fail if the code uses .toString()
        verify(valueOperations).set(eq("key"), eq("{\"testKey\":\"testValue\"}"), eq(100L));
    }
}
