package com.rediscode.use;

import com.rediscode.use.lock.JedisUtil;
import com.rediscode.use.lock.RedisTest;
import com.rediscode.use.lock.RedisTest1;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class LockTest {

    @Test
    public void testSetNx(){
        JedisUtil jedisUtil = new JedisUtil();
        Jedis jedis = jedisUtil.getJedis();
        System.out.println("设置setnx成功："+RedisTest.tryLock(jedis,"2022-05-28"));
    }

    @Test
    public void testSetNx1(){
        JedisUtil jedisUtil = new JedisUtil();
        Jedis jedis = jedisUtil.getJedis();
        System.out.println("设置setnx成功："+ RedisTest1.tryLock(jedis,"2022-05-28"));
    }

    @Test
    public void testSetNx1Lua(){
        JedisUtil jedisUtil = new JedisUtil();
        Jedis jedis = jedisUtil.getJedis();
        RedisTest1.testLua(jedis);
    }
}
