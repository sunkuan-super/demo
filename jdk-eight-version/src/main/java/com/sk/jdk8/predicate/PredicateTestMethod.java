package com.sk.jdk8.predicate;

/**
 * @Title: PredicateTestMethod
 * @Package: com.sk.jdk8.predicate
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 10:33
 */


import java.util.function.Predicate;

/**
 * java.util.fuction.Predicate<T接口>
 * 作用：对某种数据基类型的的数据进行判断，返回一个boolean值
 * <p>
 * predicate 接口包含一个抽象方法
 * boolean test(T t) :用来对指定数据类型数据进行判断的方法
 * 结果：符号条件，返回true
 * 不符合条件，返回false
 */
public class PredicateTestMethod {
    /**
     * 定义一个方法
     * 参数传递一个String类型的字符串
     * 传递一个Predicate接口，泛型使用String
     * 使用Predicate的方法test对字符串进行判断，并把判断的结果返回
     */
    public static boolean checkString(String arg, Predicate<String> predicate) {
        return predicate.test(arg);
    }

    public static void main(String[] args) {
        String s = "aaaaa";
        boolean flag = checkString(s, (arg) -> {
            if (arg.length() > 2) {
                return true;
            } else {
                return false;
            }
        });
        System.out.println(flag);

        //优化
        boolean flag2 = checkString(s,arg -> arg.length()>2);
        System.out.println(flag2);
    }
}
