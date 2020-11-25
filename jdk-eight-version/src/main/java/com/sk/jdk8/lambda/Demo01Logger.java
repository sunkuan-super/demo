package com.sk.jdk8.lambda;

/**
 * @Title: Demo01Logger
 * @Package: com.sk.jdk8.lambda
 * @Description: 日志案例
 * @Author: sunkuan
 * @Date: 2020/6/28 - 11:15
 */

/**
 * 发现以下代码存在一些行呢个浪费的问题
 * 调用showLog方法，传递的第二个参数是一个拼接后的字符串
 * 先把字符串拼接好，然后再调用showLog方法，showLog()方法中如果传递的日志等级不是1级，那么就不会如此拼接后的字符串，
 * 所以感觉字符串就白拼接了，存在了浪费
 */
public class Demo01Logger {
    public static void showLog(int level,String message) {
        if(level == 1){
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        //定义三个日志信息
        String msg1 = "hello";
        String msg2 = "World";
        String msg3 = "Java";

        showLog(1,msg1 + msg2 + msg3);


    }
}
