package com.practice.sk.rabbitmq.ps;

import com.practice.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: Consumer
 * @Package: com.practice.sk.rabbitmq.ps
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 11:13
 */
public class Consumer {

    private static final String QUEUE_NAME = "test_ps_fanout_queue1";

    private final static String EXCHANGE_NAME = "test_ps_fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        // 队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //保证一次只下发一个
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
