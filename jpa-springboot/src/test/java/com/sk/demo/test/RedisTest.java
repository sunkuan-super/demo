package com.sk.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title: RedisTest
 * @Package: com.sk.demo.test
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/23 - 15:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        //从redis中获取key为user.add对应的value
       Object userListJson =  redisTemplate.boundValueOps("user.add").get();
       if(userListJson == null) {
           System.out.println(userListJson);
           //将key user.add value 111存入redis中
           redisTemplate.boundValueOps("user.add").set(111);
           System.out.println("插入redis成功");
       }else {
           System.out.println("redis里的数据是" + userListJson);
           //删除redis的key user.add
           redisTemplate.delete("user.add");
           System.out.println("删除成功");
       }

    }
}
