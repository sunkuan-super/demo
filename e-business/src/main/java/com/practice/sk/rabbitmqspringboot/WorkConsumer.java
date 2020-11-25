package com.practice.sk.rabbitmqspringboot;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title: WorkConsumer
 * @Package: com.practice.sk.rabbitmq_springboot
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/18 - 10:31
 */
@Component
public class WorkConsumer {
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive1(String message){
        System.out.println("message1 = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String message){
        System.out.println("message2 = " + message);
    }
}
