package com.aspirecn.sk.test;

import com.aspirecn.sk.EBusinessApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title: TestRabbitMQ
 * @Package: com.aspirecn.sk.test
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 17:24
 */
@SpringBootTest(classes = EBusinessApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 简单模型
    @Test
    public void testSimple(){
        rabbitTemplate.convertAndSend("hello","hello world");
    }

    // work 模式
    @Test
    public void testWork(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","hello work " + i);
        }
    }

    // 发布订阅模式------fanout广播
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("fanout_exchange","","Fanout的模型发送消息");
    }

    // route 路由模式
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("direct_exchange","error","发送info的key的路由信息");
    }

    // topic 动态路由  订阅模式
    @Test
    public void testTopic(){ rabbitTemplate.convertAndSend("topic_exchange","user.save","user.save路由消息");}
}
