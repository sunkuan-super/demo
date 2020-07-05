package com.aspirecn.jdk8.consumer;

import java.util.function.Consumer;

/**
 * @Title: ConsumerDemo
 * @Package: com.aspirecn.jdk8.consumer
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/28 - 16:37
 */
public class ConsumerDemo {

    /**
     * 此方法先将整个的逻辑写好
     *
     * @param consumer1 consumer1
     * @param consumer2 consumer2
     * @param array 数组
     */
    public static void printInfo(Consumer<String> consumer1, Consumer<String> consumer2, String[] array) {
        for (String s : array) {
            consumer1.andThen(consumer2).accept(s);
        }
    }


    public static void main(String[] args) {
        String[] array = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男"};

        // 调用方法只需要写函数式接口的实现即可，因为整个的逻辑已写好
        printInfo((String string) -> {
                    System.out.println(string.split(",")[0]);
                },
                (String string) -> {
                    System.out.println(string.split(",")[1]);
                },
                array
        );

        //写Lambda表达式时候主要关注 重写的方法的参数
    }
}
