package com.rediscode.use.lock;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;

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

    /**
     * setnx + px放在一起 lua脚本，保证原子性
     * @param jedis
     */
    public static void testLua(Jedis jedis){
        String luaStr = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";
        ArrayList<String> keys = new ArrayList<>();
        keys.add("nxpxKey");
        ArrayList<String> args = new ArrayList<>();
        args.add("nxpxKey-Value");
        //单位是秒
        args.add("100000");
        Object result = jedis.eval(luaStr,keys,args);
        System.out.println("执行结果lua脚本："+result);
    }
}
