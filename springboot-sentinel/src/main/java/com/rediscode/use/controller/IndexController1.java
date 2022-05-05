package com.rediscode.use.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 扣减库存测试
 */
@RestController
public class IndexController1 {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController1.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    @RequestMapping("/deduct_stock")
    public String deductStock(){
        synchronized (this){
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                int realStock = stock-1;
                stringRedisTemplate.opsForValue().set("stock",realStock+"");
                System.out.println("扣减成功，剩余库存："+realStock+"");
            }else{
                System.out.println("扣减失败，库存不足");
            }
        }
        return "";
    }
}
