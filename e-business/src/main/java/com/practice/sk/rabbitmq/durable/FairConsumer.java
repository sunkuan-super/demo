package com.practice.sk.rabbitmq.durable;

import com.practice.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: FairConsum
 * @Package: com.practice.sk.rabbitmq.simplequeue
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/15 - 15:08
 */
public class FairConsumer {
    private static final String QUEUE_NMAE = "test_fair_durable_queue";


    /**
     * 消费者从队列中获取消息，服务端如何知道消息已经被消费呢？
     *
     * 模式1：自动确认
     * 只要消息从队列中获取，无论消费者获取到消息后是否成功消息，都认为是消息已经成功消费。
     *
     * 模式2：手动确认
     * 消费者从队列中获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，如果消费者一直没有反馈，那么该消息将一直处于不可用状态。
     *
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //持久化
        boolean durable = true;

        channel.queueDeclare(QUEUE_NMAE, durable, false, false, null);

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

        /**
         * 1、boolean autoAck = true;自动确认模式，一旦RabbitMQ将消息分发给消费者就会自动在内存中删除该条消息。这种情况，如果杀死正在执行的消费者，则该消息就会丢失。
         *
         * 2、boolean autoAck = false;手动确认模式，如果有一个消费者挂掉，RabbitMQ就会将该消息交付给其他消费者。只有当消费者发出一个应答ACK，即告知RabbitMQ我已经处理完了这条消息了！然后 RabbitMQ才会将该消息从内存中删除。
         *
         * 3、但是还存在一个问题，如果在分发消息的过程，RabbitMQ服务器挂掉了，消息还是会丢失！！
         *      声明一个队列
         *      boolean durable = true;
         *      channel.queueDeclare(QUEUE_NAME,durable,false,false,null);
         *
         *      解决方式：声明队列的时候，有一个持久化该队列的参数，设置成 true 即可。
         *      注：通过参数修改此队列时，保证这个队列是个新的队列，不重复定义（即之前假如声明过一个非持久化的队列，不能再将此队列声明为持久化队列）
         */

        // 自动确认设为false,即手动确认
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NMAE, autoAck, defaultConsumer);
    }
}
