package com.example.demo.util.redis.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * description: redis 发布订阅中的发布者
 *
 * @auth guoshuai
 * @since 2018/8/15
 */
@Component
public class Publisher {

    private static JedisPool jedisPool;

    @Autowired
    public Publisher(JedisPool jedisPool) {
        Publisher.jedisPool = jedisPool;
    }

    /**
     * 发布消息到redis
     */
    public static void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Jedis jedis = jedisPool.getResource();   //连接池中取出一个连接
                String line = null;
                try {
                    line = reader.readLine();
                    if (!"quit".equals(line)) {
                        System.out.println("line === "+line);
                        jedis.publish("mychannel", line);   //从 mychannel 的频道上推送消息
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
