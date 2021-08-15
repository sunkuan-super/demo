package com.practice.sk.rabbitmq.confirm;

import com.practice.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: SendBatchConfirm
 * @Package: com.practice.sk.rabbitmq.confirm
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 14:46
 */
public class SendBatchConfirm {

    private static final String QUEUE_NAME = "test_queue_confirm1";

    private static final int batchNum = 10;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //生产者调用confirmSelect将channel设置为confirm模式，注意设置为事务模式就不能再设置为confirm模式
        channel.confirmSelect();

        String msg = "Hello confirm msg batch";

        //批量发送
        for (int i = 0; i < batchNum; i++) {
            channel.basicPublish("", QUEUE_NAME, null, (msg+i).getBytes());
        }

        if (!channel.waitForConfirms()) {
            System.out.println("message send batch failed");
        } else {
            System.out.println("message send batch success");
        }

        channel.close();
        connection.close();


    }
}
