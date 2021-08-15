package com.sk.jdk8.function;

import java.util.function.Function;

/**
 * @Title: FunctionDemo
 * @Package: com.sk.jdk8.function
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 14:08
 */

/*
    String str = "赵丽颖,20";
    1. 将字符串截取数字年龄部分，得到字符串；
    2. 将上一步的字符串转换成为int类型的数字；
    3. 将上一步的int数字加100，得到结果int数字。
 */
public class FunctionDemo {
    public static void change(String s, Function<String, String> function1, Function<String, Integer> function2, Function<Integer, Integer> function3) {
        function1.andThen(function2).andThen(function3).apply(s);
    }

    public static void main(String[] args) {
        String str = "赵丽颖,20";

        change(str,
                //"赵丽颖,20"->"20"
                (string) -> string.split(",")[1],
                //"20"->20
                ageStr -> Integer.parseInt(ageStr),
                //20->120
                age -> age + 100);
    }
}
