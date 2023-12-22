package com.campuswindow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class CampusWindowApplicationTests {

    @Autowired
    StringRedisTemplate template;
    @Test
    void contextLoads() throws InterruptedException {
        template.opsForValue().set("1","1",1, TimeUnit.MINUTES);
        String s = template.opsForValue().get("1");
        System.out.println("s = " + s);
        while (template.opsForValue().get("1") != null) {
            System.out.println(template.getExpire("1"));
            Thread.sleep(5000);
        }
    }

}
