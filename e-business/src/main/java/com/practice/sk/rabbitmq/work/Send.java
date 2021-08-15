package com.practice.sk.rabbitmq.work;

import com.practice.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: Send
 * @Package: com.practice.sk.rabbitmq.simplequeue
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/15 - 13:36
 */
public class Send {
    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 50; i++) {
            String msg = "hello simple " + i;

            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

            System.out.println("发送完毕");
            Thread.sleep(20);
        }
        channel.close();
        connection.close();

    }
}
