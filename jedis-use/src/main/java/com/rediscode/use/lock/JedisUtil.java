package com.rediscode.use.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {

    private String address = "192.168.125.100";
    private int port = 6379;
    private String password = "zhuge";
    private int timeout = 10000;
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
    private int maxTotal = -1;
    private int maxIdle=200;
    private int maxWait = 10000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
    private boolean testOnBorrow=true;

    private JedisPool jedisPool = null;

    public JedisUtil() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(testOnBorrow);
            jedisPool = new JedisPool(config,address,port,timeout,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Jedis getJedis(){
        if (jedisPool!=null){
            return jedisPool.getResource();
        }
        return null;
    }
}
