package com.rediscode.use;

import redis.clients.jedis.util.JedisClusterHashTag;

import static redis.clients.jedis.util.JedisClusterCRC16.getCRC16;

/**
 * jedis的这个客户端槽位计算
 */
public class CRCTest {

    public static void main(String[] args) {
        String key = "hongmi";
        String value = "k30";
        String key16= JedisClusterHashTag.getHashTag(key);
        System.out.println( getCRC16(key) & 16383);

    }
}
