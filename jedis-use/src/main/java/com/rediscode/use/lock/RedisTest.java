package com.rediscode.use.lock;

import redis.clients.jedis.Jedis;

public class RedisTest {

    private static final String LOCK_SUCCESS="OK";
    private static final String SET_IF_NOT_EXIST="NX";
    private static final String SET_WITH_EXPIRE_TIME="PX";

    /**
     * 获取setnx
     * @param jedis
     * @param lockKey
     * @return
     */
    public static boolean tryLock(Jedis jedis,String lockKey){
        long result = jedis.setnx(lockKey,"1");
        return result>0;
    }
}
