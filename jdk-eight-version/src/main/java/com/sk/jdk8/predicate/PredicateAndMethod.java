package com.sk.jdk8.predicate;

/**
 * @Title: PredicateAndMethod
 * @Package: com.sk.jdk8.predicate
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 10:46
 */

import java.util.function.Predicate;

/**
 * 逻辑表达式：可以来凝结郭个判断的条件
 * &&： 与运算符，有false则false
 * ||: 或运算符，有true则true
 * !: 取反
 *
 * 需求：判断一个字符串，有两个判断的条件
 * 1、判断字符串的长度是否大于5
 * 2、判断字符串中是否包含a
 *
 * Predicate接口中有一个方法and,表示并且关系，也可以用于连接两个判断条件
 *  default Predicate<T> and(Predicate<? super T> other) {
 *         Objects.requireNonNull(other);
 *         return (t) -> test(t) && other.test(t);
 *  }
 */
public class PredicateAndMethod {
     /**
      * 定义一个方法，方法的参数，传递一个字符串
      *  传递两个Predicate接口
      *      一个用于判断字符串的长度是否大于5
      *      另一个用于判断字符串中是否包含a
      */
     public static boolean checkString(String s,Predicate<String> predicate1,Predicate<String> predicate2) {
         //return predicate1.test(s) && predicate2.test(s);
         //等价于return predicate1.test(s) && predicate2.test(s);
         return predicate1.and(predicate2).test(s);
     }

    public static void main(String[] args) {
        boolean flag = checkString("abcdef",s -> s.length()>5,s -> !s.contains("a") );
        System.out.println(flag);
    }
}
