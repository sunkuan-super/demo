package com.aspirecn.sk.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title: ConnectionUtils
 * @Package: com.aspirecn.sk.rabbitmq.util
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/15 - 13:34
 */
public class ConnectionUtils {
    public static Connection getConnection() throws IOException, TimeoutException {

        // 定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();


        // 设置服务地址
        factory.setHost("localhost");

        // 设置端口
        factory.setPort(5672);

        //vhost
        factory.setVirtualHost("vhost_sms");

        //设置用户名
        factory.setUsername("sunkuan");

        //设置密码
        factory.setPassword("123456");


        return factory.newConnection();
    }
}
