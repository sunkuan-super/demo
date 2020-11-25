package com.practice.sk.rabbitmq.confirm;

import com.practice.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * @Title: SendConfirmAsync
 * @Package: com.aspirecn.sk.rabbitmq.confirm
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 15:15
 */
public class SendConfirmAsync {

    private static final String QUEUE_NAME = "test_queue_confirm1";

    private static final int batchNum = 10;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //指定消息的投递模式 :confirm 确认模式
        //生产者调用confirmSelect将channel设置为confirm模式，注意设置为事务模式就不能再设置为confirm模式
        channel.confirmSelect();

        //未确认的消息标识
        final SortedSet<Long> confirmSet = Collections.synchronizedNavigableSet(new TreeSet<Long>());

//        String msg = "Hello confirm msg";
//
//        for (int i = 0; i < batchNum; i++) {
//            channel.basicPublish("", QUEUE_NAME, null, (msg + "哈哈哈").getBytes());
//            System.out.println(msg);
//        }

        //添加一个确认监听，这里就不关闭连接了，为了能保证接收到监听消息

        channel.addConfirmListener(new ConfirmListener() {

            //Ack成功的回调函数
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("ack: deliveryTag " + deliveryTag + "multiple： " + multiple);
            }

            //Ack失败的回调函数
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("nack: deliverTag " + deliveryTag + "mutiple: " + multiple);
            }
        });

        String msgStr = "sssssssssssssssss";
        while (true) {
            long nextPublishSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, msgStr.getBytes());
            confirmSet.add(nextPublishSeqNo);
        }


    }
}
