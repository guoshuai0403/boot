package com.example.demo.util.redis.subscribe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import static org.junit.Assert.*;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChannelTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void get() throws Exception {
        Subscriber subscriber = new Subscriber();
        String channel = "mychannel";
        String channel1 = "mychannel1";
        String channel2 = "mychannel2";

        System.out.println("--------------------------------------------------------------");
        new Thread(new Runnable() {
            @Override
            public void run() {
                jedisPool.getResource().subscribe(subscriber, channel, channel1, channel2);
            }
        }).start();

        jedisPool.getResource().publish(channel, "asdfasdfsd");

        jedisPool.getResource().publish(channel, "111111");

        jedisPool.getResource().publish(channel, "aaaaaaaaaaaaaaaaa");

        jedisPool.getResource().publish(channel1, "bbbbbbbbbbbbbbb");
        jedisPool.getResource().publish(channel2, "ccccccccccccccccc");

//        // 订阅
//        Channel.get(subscriber, channel);
//
//        // 发布
//        Publisher.run();



    }

}