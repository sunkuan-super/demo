package com.practice.sk.rabbitmqspringboot;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title: Consumer
 * @Package: com.practice.sk.rabbitmq_springboot
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 17:31
 */
@Component
// 持久化 非独占 不是自动删除的队列
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class Consumer {

    @RabbitHandler
    public void receive1(String message){
        System.out.println("消费者  message : " + message);
    }
}
