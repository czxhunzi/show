package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootApplicationTests {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testString(){
        redisTemplate.opsForValue().set("city","北京");
        Object city = redisTemplate.opsForValue().get("city");
        System.out.println(city);
    }
    @Test
    public void testHash(){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("id","age",10);
    }


}
