package com.aspirecn.sk.rabbitmq.work;

import com.aspirecn.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: Consum
 * @Package: com.aspirecn.sk.rabbitmq.simplequeue
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/15 - 13:46
 */
public class Consumer2 {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body, "utf-8");
                System.out.println("new api consum : " + msg);

                Thread.sleep(1000);
            }
        };

        //监听队列
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

    }
}
