package com.practice.sk.rabbitmq.tx;

import com.practice.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: TxConsumer
 * @Package: com.practice.sk.rabbitmq.tx
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 13:44
 */
public class TxConsumer {

    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body,"utf-8");
                System.out.println("recv[tx] msg :" + msg);
            }
        };

        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);
    }
}
