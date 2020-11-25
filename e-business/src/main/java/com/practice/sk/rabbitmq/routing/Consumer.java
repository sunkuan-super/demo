package com.practice.sk.rabbitmq.routing;

import com.practice.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: Consumer
 * @Package: com.aspirecn.sk.rabbitmq.ps
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 11:13
 */
public class Consumer {

    private static final String QUEUE_NAME = "test_ps_direct_queue1";

    private final static String EXCHANGE_NAME = "test_ps_direct_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        /**
         * 参数明细
         * 1、queue 队列名称
         * 2、durable 是否持久化，如果持久化，mq重启后队列还在
         * 3、exclusive 是否独占连接，队列只允许在该连接中访问，如果connection连接关闭队列则自动删除,如果将此参数设置true可用于临时队列的创建
         * 4、autoDelete 自动删除，队列不再使用时是否自动删除此队列，如果将此参数和exclusive参数设置为true就可以实现临时队列（队列不用了就自动删除）
         * 5、arguments 参数，可以设置一个队列的扩展参数，比如：可设置存活时间
         */
        // 队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        String routingKey = "error";
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,routingKey);

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
