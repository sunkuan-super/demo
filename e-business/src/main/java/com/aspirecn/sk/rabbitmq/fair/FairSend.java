package com.aspirecn.sk.rabbitmq.fair;

import com.aspirecn.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: FairSend
 * @Package: com.aspirecn.sk.rabbitmq.simplequeue
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/15 - 15:30
 */
public class FairSend {
    private static final String QUEUE_NAME = "test_fair_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        boolean durable = false;
        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        /**
         * 队列只有在收到消费者发回的上一条消息 ack 确认后，才会向该消费者发送下一条消息
         * 发送窗口大小为 1
         * 即同一时刻服务器只会发送一条消息给消费者
         */
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        for (int i = 0; i < 100; i++) {
            String msg = "hello simple " + i;

            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

            System.out.println("发送完毕");
            Thread.sleep(20);
        }
        channel.close();
        connection.close();

    }
}
