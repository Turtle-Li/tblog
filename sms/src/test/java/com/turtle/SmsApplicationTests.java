package com.turtle;

import com.turtle.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SmsApplicationTests {
    @Resource
    RedisUtil redisUtil;
    @Test
    void contextLoads() {
        redisUtil.set("a","123");
        System.out.println(redisUtil.get("a"));
    }

}
