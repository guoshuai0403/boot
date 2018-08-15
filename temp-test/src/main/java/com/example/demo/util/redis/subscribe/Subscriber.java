package com.example.demo.util.redis.subscribe;

import redis.clients.jedis.JedisPubSub;

/**
 * description: redis 发布订阅中的订阅者
 *
 * @auth guoshuai
 * @since 2018/8/15
 */
public class Subscriber extends JedisPubSub {

    /**
     * 收到消息会调用
     * @param channel - 频道
     * @param message - 消息
     */
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
    }

    /**
     * 订阅了频道会调用
     * @param channel - 频道
     * @param subscribedChannels - 订阅频道的数量
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);
        super.onSubscribe(channel, subscribedChannels);
    }

    /**
     * 取消订阅会用到
     * @param channel - 频道
     * @param subscribedChannels - 订阅频道的数量
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);
        super.onUnsubscribe(channel, subscribedChannels);
    }
}
