package com.example.demo.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.List;

/**
 * Created by guoshuai on 2018/8/14 0014.
 */
@Component
public class RedisUtil extends Jedis {

    private static JedisPool jedisPool;

    /** Only set the key if it does not already exist */
    private static String NX = "NX";

    /** Only set the key if it already exist. */
    private static String XX = "XX";

    /** expire time units: EX = seconds */
    private static String PX = "PX";
    /** expire time units: PX = milliseconds */
    private static String EX = "EX";

    @Autowired
    public RedisUtil(JedisPool jedisPool){
        RedisUtil.jedisPool = jedisPool;
    }

    /**
     * 根据key删除
     * @param key
     * @return
     */
    public static boolean delByKey(String key){
        Long del = jedisPool.getResource().del(key);
        if (del != null && del > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据Key获取剩余的有效毫秒数
     * @param key
     * @return
     */
    public static Long getPersist(String key){
        return jedisPool.getResource().persist(key);
    }

    // ------S操作字符串-------------------------------------------
    /**
     * 往redis中存入字符串
     * @param key
     * @param value
     * @return
     */
    public static boolean putString(String key, String value){
        String result = jedisPool.getResource().set(key, value);
        if ("ok".equalsIgnoreCase(result)) {
            return true;
        }
        return false;
    }

    /**
     * 往redis中存入字符串, 并且给Key增加有效时间，时间单位是毫秒
     * @param key - 键
     * @param value - 值
     * @param milliseconds - 有效时间（毫秒）
     * @return
     */
    public static boolean putString(String key, String value, Long milliseconds){
        if (RedisUtil.putString(key, value)) {
            Long pexpire = jedisPool.getResource().pexpire(key, milliseconds);
            if (pexpire != null && pexpire > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 往redis中存入值
     * isExist  = false: 仅当Key不存在时存入
     * isExist  = true: 仅当Key存在时存入
     * @param key - 键
     * @param value - 值
     * @param isExist - 是否存在
     * @return
     */
    public static boolean putString(String key, String value, boolean isExist){
        String result = jedisPool.getResource().set(key, value, isExist ? RedisUtil.XX : RedisUtil.NX);
        if ("ok".equalsIgnoreCase(result)) {
            return true;
        }
        return false;
    }

    /**
     * 往redis中存入值，并制定key的有效时长（单位毫秒）
     * isExist  = false: 仅当Key不存在时存入
     * isExist  = true: 仅当Key存在时存入
     * @param key - 键
     * @param value - 值
     * @param isExist - 是否存在
     * @param milliseconds - key的有效时长（毫秒）
     * @return
     */
    public static boolean putString(String key, String value, boolean isExist, long milliseconds){
        String result = jedisPool.getResource().set(key, value,
                isExist ? RedisUtil.XX : RedisUtil.NX, RedisUtil.EX, milliseconds);
        if ("ok".equalsIgnoreCase(result)) {
            return true;
        }
        return false;
    }

    /**
     * redis 字符串增量拼接，
     * 如果Key不存在，增进行新增， 效果同put(String key, String value)
     * @param key
     * @param incrementValue
     * @return
     */
    public static boolean appendString(String key, String incrementValue){
        Long append = jedisPool.getResource().append(key, incrementValue);
        if (append != null && append > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据Key获取字符串值
     * @param key
     * @return
     */
    public static String getString(String key){
        return jedisPool.getResource().get(key);
    }

    /**
     * 根据key给值自增1，如果Key不存在则结果返回1
     * 如果key对应的value值不是整整则返回null
     * @param key
     * @return
     */
    public static Long increment (String key) {
        try {
            return jedisPool.getResource().incr(key);
        } catch (JedisDataException e) {
            return null;
        }
    }
    // ------E操作字符串-------------------------------------------

    // ------S操作List列表-------------------------------------------
    public static void put(String key, List<?> list){
//        jedisPool.getResource().l
    }

    // ------E操作List列表-------------------------------------------

}
