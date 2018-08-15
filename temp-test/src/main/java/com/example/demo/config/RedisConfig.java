package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by guoshuai on 2018/8/14 0014.
 */
@Configuration
public class RedisConfig {

    private String host = "47.104.108.66";

    private int port = 4951;

    private int timeout = 1000*3;

    private String password = "yongmin521";


    @Bean
    public JedisPool initJedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }
}
