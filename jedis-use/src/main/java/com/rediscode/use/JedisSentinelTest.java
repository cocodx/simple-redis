package com.rediscode.use;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * 连接sentinel
 */
public class JedisSentinelTest {

    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(5);
        config.setMaxIdle(10);
        config.setMaxTotal(20);

        String masterName = "mymaster";
        Set<String> sentinels = new HashSet<>();
        sentinels.add(new HostAndPort("192.168.125.100",26379).toString());
        sentinels.add(new HostAndPort("192.168.125.60",26379).toString());
        sentinels.add(new HostAndPort("192.168.125.61",26379).toString());
        //jedisSentinelPool并不是说与sentinel建立的连接池，而是通过sentinel发现redis主节点与其建立连接。
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName,sentinels,config,3000,null);
        Jedis jedis = null;
        try{
            jedis = jedisSentinelPool.getResource();
            System.out.println(jedis.set("sentinel","zhuge"));
            System.out.println(jedis.get("sentinel"));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
    }
}
