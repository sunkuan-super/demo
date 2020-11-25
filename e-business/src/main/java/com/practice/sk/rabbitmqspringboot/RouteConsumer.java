package com.practice.sk.rabbitmqspringboot;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title: RouteConsumer
 * @Package: com.aspirecn.sk.rabbitmq_springboot
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/18 - 11:04
 */
@Component
public class RouteConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "direct_exchange", type = "direct") ,// 指定交换机名称和类型
                    key = {"info","error","warn"}
            )
    })
    public void receive1(String message) {
        System.out.println("message1: " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "direct_exchange",type = "direct"),
                    key = {"error"}
            )
    })
    public void receive2(String message){
        System.out.println("message2: " + message);
    }
}
