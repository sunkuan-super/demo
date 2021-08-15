package com.practice.sk.rabbitmq.confirm;

import com.practice.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: ConsumerConfirm1
 * @Package: com.practice.sk.rabbitmq.confirm
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 14:25
 */
public class ConsumerConfirm {

    private static final String QUEUE_NAME = "test_queue_confirm1";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body,"utf-8");
                System.out.println("recv[confirm] msg :" + msg);
            }
        };

        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);
    }

}
