package com.rediscode.use;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.List;

public class JedisSingleTest {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.125.100",6379,3000,null);
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            System.out.println(jedis.set("靓仔2333","human"));
            System.out.println(jedis.get("靓仔2333"));

            //设置管道Pipeline
            Pipeline pipeline = jedis.pipelined();
            for (int i = 0; i < 10; i++) {
                pipeline.incr("roomNumber");
                pipeline.set("green mountain"+i,"green view");
                pipeline.get("bugWater");
            }
            List<Object> results = pipeline.syncAndReturnAll();
            System.out.println(results);

            String script = " local count = redis.call('get',KEYS[1]) "+
                    " local a = tonumber(count) " +
                    " local b = tonumber(ARGV[1]) "+
                    " if a>= b then "+
                    " redis.call('set', KEYS[1],a-b) "+
                    " return 1 "+
                    " end "+
                    " return 0 ";
            Object obj = jedis.eval(script, Arrays.asList("product_count_10016"),Arrays.asList("10"));
            System.out.println(obj);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
