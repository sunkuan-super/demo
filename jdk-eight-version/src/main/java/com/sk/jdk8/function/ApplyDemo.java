package com.sk.jdk8.function;

import java.util.function.Function;

/*
        java.util.function.Function<T,R>接口用来根据一个类型的数据得到另一个类型的数据，
        前者称为前置条件，后者称为后置条件。
        Function接口中最主要的抽象方法为：R apply(T t)，根据类型T的参数获取类型R的结果。
        使用的场景例如：将String类型转换为Integer类型。

 */

public class ApplyDemo {

    /*
        定义一个方法
        方法的参数传递一个字符串类型的整数
        方法的参数传递一个Function接口,泛型使用<String,Integer>
        使用Function接口中的方法apply,把字符串类型的整数,转换为Integer类型的整数
     */
    public static void change(String s,Function<String,Integer> function) {
        Integer apply = function.apply(s);
        System.out.println(apply);
    }

    public static void main(String[] args) {
        String s = "1234";
        change(s,str->Integer.parseInt(str) + 9);
    }
}
