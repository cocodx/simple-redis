package com.rediscode.use;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class,args);
    }

    @Bean
    public Redisson redisson(){
        //此为单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.125.100:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
