package com.rediscode.use.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/test_sentinel")
    public void testSentinel(){
        int i=1;
        while (true){
            try{
                stringRedisTemplate.opsForValue().set("zhuge"+i,i+"");
                System.out.println("设置key："+"zhuge"+i);
                i++;
                Thread.sleep(1000);
            }catch (Exception e){
                logger.error("error:",e);
            }
        }
    }

    @RequestMapping("/testCluster")
    public void testCluster(){
        int i=1;
        while (true){
            try{
                stringRedisTemplate.opsForValue().set("zhuge"+i,i+"");
                System.out.println("设置key："+"zhuge"+i);
                i++;
                Thread.sleep(1000);
            }catch (Exception e){
                logger.error("error:",e);
            }
        }
    }
}
