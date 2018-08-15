package com.example.demo.util.redis;

import net.bytebuddy.description.type.TypeDescription;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.ValidationEvent;

import static org.junit.Assert.*;

/**
 * Created by guoshuai on 2018/8/14 0014.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
    @Test
    public void putString1() throws Exception {

        RedisUtil.putString("key", "aaaaaaaaaaaa", false);

        RedisUtil.putString("key11", "aaaaaaaaaaaa", false);

        RedisUtil.putString("key1", "aaaaaaaaaaaa", true);

        RedisUtil.putString("key12", "aaaaaaaaaaaa", true);

        RedisUtil.putString("key5", "aaaaaaaaaaaa", false, 1000*120l);

        RedisUtil.putString("key13", "aaaaaaaaaaaa", false, 1000*120l);

        RedisUtil.putString("key6", "aaaaaaaaaaaa", true, 1000*120l);

        RedisUtil.putString("key12", "aaaaaaaaaaaa", true, 1000*120l);

        System.out.println("RedisUtil.getPersist(\"key13\") ======= "+RedisUtil.getPersist("key13"));
        System.out.println("RedisUtil.getPersist(\"key6\") ========"+RedisUtil.getPersist("key6"));
        System.out.println("RedisUtil.getPersist(\"key12\") ========"+RedisUtil.getPersist("key12"));


    }

    @Test
    public void putString() throws Exception {

        RedisUtil.putString("key11", "the value", 1000*120l);
    }

    @Test
    public void put1() throws Exception {

        Long key2 = RedisUtil.increment("keyaasd");
        System.out.println(key2);
    }

    @Test
    public void delByKey() throws Exception {

        RedisUtil.delByKey("key");
    }

    @Test
    public void getString() throws Exception {

        String key1 = RedisUtil.getString("key");
        System.out.println(key1);
    }

    @Test
    public void put() throws Exception {
//        RedisUtil.put("key", "1");
//        RedisUtil.append("key1", "the");

    }

}