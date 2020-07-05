package com.aspirecn.jdk8.supplier;

/**
 * @Title: Demo01Supplier
 * @Package: com.aspirecn.jdk8.supplier
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/28 - 14:40
 */

import java.util.function.Supplier;

/**
 * 常用的函数时接口
 * java.util.function.Supplier<T>接口仅包含一个无参的方法：T get().用来获取一个泛型参数指定类型的对象数据
 *
 * Supplier<T> 接口被称之为生产型接口，指定接口的泛型是什么类型，接口中的get方法就生产什么类型的数据
 */
public class Demo01Supplier {
    //定义一个方法，方法的参数传递Supplier<接口>，泛型执行String,get()方法就会返回一个String
    public static String getString(Supplier<String> supplier) {
       return supplier.get();
    }

    public static void main(String[] args) {
        //调用getString()方法，方法的参数Supplier是一个函数式接口，所以可以传递Lambda表达式
        String s = getString(()->{
            return "hupu";
        });

        System.out.println(s);

        //简化 可以sheng
        System.out.println(getString(()-> "hupu"));
    }
}
