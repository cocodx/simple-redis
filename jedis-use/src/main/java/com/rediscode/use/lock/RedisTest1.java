package com.rediscode.use.lock;

import redis.clients.jedis.Jedis;

/**
 * 设置setnx分布式锁，设置过期时间,缺乏原子性，
 * 如果在setnx和expire之间出现异常，锁无法释放
 */
public class RedisTest1 {

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
        jedis.expire(lockKey,10);
        return result>0;
    }
}
