package com.practice.sk.rabbitmq.fair;

import com.practice.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: FairConsum
 * @Package: com.aspirecn.sk.rabbitmq.simplequeue
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/15 - 15:08
 */
public class FairConsumer {
    private static final String QUEUE_NAME = "test_fair_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //持久化
        boolean durable = false;

        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        //手动应答
        channel.basicQos(1);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);


                String msg = new String(body);
                System.out.println("[consumer 1] " + msg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 手动应答
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }

            }
        };

        // 自动确认设为false,即手动确认
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, defaultConsumer);
    }
}
