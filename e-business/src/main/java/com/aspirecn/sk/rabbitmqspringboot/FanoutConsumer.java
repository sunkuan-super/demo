package com.aspirecn.sk.rabbitmqspringboot;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title: FanoutConsumer
 * @Package: com.aspirecn.sk.rabbitmq_springboot
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/18 - 10:51
 */
@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, //创建临时队列
            exchange = @Exchange(value = "fanout_exchange",type = "fanout") //绑定的交换机
            ),

    })
    public void receive1(String message){
        System.out.println("message1: " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "fanout_exchange",type = "fanout") //绑定的交换机
            ),

    })
    public void receive2(String message){
        System.out.println("message2 = " + message);
    }
}
