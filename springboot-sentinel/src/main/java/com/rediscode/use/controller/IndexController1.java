package com.rediscode.use.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController1 {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController1.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/test_sentinel1")
    public void testSentinel(){
        int i=1;
        while (true){
            try{
                redisTemplate.opsForValue().set("redisTemplate"+i,i+"");
                redisTemplate.opsForHash().put("key","field","value");
                redisTemplate.opsForList().leftPush("list","node");
                redisTemplate.opsForSet().add("keyset","1","2");
                redisTemplate.opsForZSet().add("cardNumber","19522978908",89222.00);
                System.out.println("设置key："+"redisTemplate"+i);
                i++;
                Thread.sleep(1000);
            }catch (Exception e){
                logger.error("error:",e);
            }
        }
    }
}
