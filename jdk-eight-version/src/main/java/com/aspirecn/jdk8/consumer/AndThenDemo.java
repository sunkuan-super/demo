package com.aspirecn.jdk8.consumer;

/**
 * @Title: AndThenDemo
 * @Package: com.aspirecn.jdk8.consumer
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/28 - 16:03
 */

import java.util.function.Consumer;

/**
 * Consumer接口的默认方法andThen
 * 作用：需要两个Consumer接口，可以把两个Consumer接口组合到一起，再对数据进行消费
 * <p>
 * 例如：
 * Consumer<String> con1
 * Consumer<String> con2
 * String s = "hello"
 * con1.accept(s)
 * con2.accept(s)
 * <p>
 * 连接两个Consumer接口，再进行消费
 * con1.andThen(con2).accept(s)  谁写前边谁先消费
 */
public class AndThenDemo {
    //定义一个方法，方法的参数传递一个字符串和两个Consumer接口，Consumer接口的泛型使用字符串
    public static void method(String s, Consumer<String> consumer1, Consumer<String> consumer2) {
//        consumer1.accept(s);
//        consumer2.accept(s);

        // con2连接con1，先执行con2消费数据，再执行con1消费数据
        consumer2.andThen(consumer1).accept(s);
    }

    public static void main(String[] args) {
        method("Hello",
                (String string) -> {
                    //消费方式：把字符串转换为大写输出
                    String string1 = string.toUpperCase();
                    System.out.println(string1);
                },
                (String string) -> {
                    //消费方法：把字符串装华为小写输出
                    String string2 = string.toLowerCase();
                    System.out.println(string2);
                }
        );

        //简化
        method("Hbase",
                string ->
                    //消费方式：把字符串转换为大写输出
                    System.out.println(string.toUpperCase())
                ,
                 string ->
                    //消费方法：把字符串装华为小写输出
                    System.out.println(string.toLowerCase())

        );
    }

}
