package com.rediscode.use.lock;

import redis.clients.jedis.Jedis;

/**
 * 设置setnx分布式锁，
 * 待优化：获取锁之后，出现错误，导致无法释放，一直存在
 */
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
