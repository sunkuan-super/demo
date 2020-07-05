package com.aspirecn.sk.rabbitmqspringboot;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title: TopicConsumer
 * @Package: com.aspirecn.sk.rabbitmqspringboot
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/18 - 13:50
 */
@Component
public class TopicConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange( type = "topic", name = "topic_exchange"),
            key = {"order.#","produce.*","user.*"}
    ))
    public void receive1(String message){
        System.out.println("message1: " + message);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topic_exchange", type = "topic"),
            key = {"order.#","produce.*","user.*"}
    ))
    public void receive2(String message){
        System.out.println("message2: " + message);
    }
}
