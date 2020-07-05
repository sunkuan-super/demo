package com.aspirecn.sk.rabbitmq.routing;


import com.aspirecn.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: Send
 * @Package: com.aspirecn.sk.rabbitmq.ps
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 11:01
 */
public class Send {

    private final static String EXCHANGE_NAME = "test_ps_direct_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        // 声明交换机
        // fanout 分发
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //发送消息
        String msg = "hello direct error";

        String routingKey = "info";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());

        System.out.println("Send : " + msg);

        channel.close();
        connection.close();
    }
}
