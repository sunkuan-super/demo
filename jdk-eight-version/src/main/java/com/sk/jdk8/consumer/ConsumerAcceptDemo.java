package com.sk.jdk8.consumer;

/**
 * @Title: ConsumerDemo
 * @Package: com.practice.jdk8.consumer
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/28 - 15:51
 */

import java.util.function.Consumer;

/**
 * 消费性接口，泛型指定什么类型，就可以使用accept方法消费什么类型的数据
 */
public class ConsumerAcceptDemo {
    /*
      定义一个方法
      方法的参数传递一个字符串的姓名
      方法的参数传递Consumer接口，泛型使用String
      可以使用Consumer接口雄安飞字符串的姓名
     */

    public static void method(String name, Consumer<String> consumer){
        consumer.accept(name);
    }

    public static void main(String[] args) {
        //调用method方法，传递字符串姓名，方法的另一个参数是Consumer接口，是一个函数式接口，所以可以出啊降低lambda表达式
        method("赵丽颖",(name) -> {
            System.out.println(name);

            String newName = new StringBuffer(name).reverse().toString();
            System.out.println(newName);
        });
    }
}
