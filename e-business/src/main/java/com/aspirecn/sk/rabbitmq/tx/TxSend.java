package com.aspirecn.sk.rabbitmq.tx;

import com.aspirecn.sk.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.expression.spel.ast.NullLiteral;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: TxSend
 * @Package: com.aspirecn.sk.rabbitmq.tx
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/17 - 13:34
 */
public class TxSend {

    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();

        //从连接中获取一个通道
        Channel channel = connection.createChannel();

        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String msg = "hello tx message";

        try {
            //开启事务
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println(1/0);
            System.out.println("send " + msg);
            channel.txCommit();
        }catch (Exception e){
            channel.txRollback();
            System.out.println("send message txRollback");
        }finally {
            channel.close();
            connection.close();
        }

    }
}
