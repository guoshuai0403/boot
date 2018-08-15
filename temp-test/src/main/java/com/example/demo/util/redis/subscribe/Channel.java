package com.example.demo.util.redis.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * description: 订阅频道
 *
 * @auth guoshuai
 * @since 2018/8/15
 */
@Component
public class Channel{

    private static JedisPool jedisPool;

    @Autowired
    public Channel(JedisPool jedisPool) {
        Channel.jedisPool = jedisPool;
    }

    public static void get(JedisPubSub jedisPubSub, String channel){
//        Subscriber subscriber = new Subscriber();
//        String channel = "mychannel";
        System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //取出一个连接
                    //通过subscribe 的api去订阅，入参是订阅者和频道名
                    jedisPool.getResource().subscribe(jedisPubSub, channel);
                } catch (Exception e) {
                    System.out.println(String.format("subsrcibe channel error, %s", e));
                }
            }
        });

        System.out.println("发布者执行完毕");
    }

}
