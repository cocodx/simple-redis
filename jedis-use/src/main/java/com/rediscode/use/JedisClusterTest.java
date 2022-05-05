package com.rediscode.use;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * key = JedisClusterHashTag.getHashTag(key);
 *             return getCRC16(key) & 16383;
 * java编写连接cluster客户端
 */
public class JedisClusterTest {

    public static void main(String[] args) {
        //这个构造方法参数是不ok的，<redis>不是<connection>,!!!
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        GenericObjectPoolConfig<Connection> connectionGenericObjectPoolConfig = new GenericObjectPoolConfig<>();
        ConnectionPoolConfig connectionPoolConfig = new ConnectionPoolConfig();
        connectionPoolConfig.setMaxTotal(20);
        connectionPoolConfig.setMaxIdle(10);
        connectionPoolConfig.setMinIdle(5);

        Set<HostAndPort> clusterNodes = new HashSet<>();
        clusterNodes.add(new HostAndPort("192.168.125.100",6379));
        clusterNodes.add(new HostAndPort("192.168.125.100",8001));
        clusterNodes.add(new HostAndPort("192.168.125.60",6379));
        clusterNodes.add(new HostAndPort("192.168.125.60",8001));
        clusterNodes.add(new HostAndPort("192.168.125.61",6379));
        clusterNodes.add(new HostAndPort("192.168.125.61",8001));


        /**
         *     public JedisCluster(Set<HostAndPort> clusterNodes, int connectionTimeout, int soTimeout, int maxAttempts, String password, GenericObjectPoolConfig<Connection> poolConfig) {
         *         this((Set)clusterNodes, connectionTimeout, soTimeout, maxAttempts, password, (String)null, poolConfig);
         *     }
         *
         */
        JedisCluster jedisCluster = new JedisCluster(clusterNodes,5000,1000,1000,"zhuge",connectionPoolConfig);
        System.out.println(jedisCluster.set("cluster","zhuge"));
        System.out.println(jedisCluster.get("cluster"));
        System.out.println("yibao");

    }
}
