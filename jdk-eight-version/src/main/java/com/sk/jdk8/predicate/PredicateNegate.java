package com.sk.jdk8.predicate;

import java.util.function.Predicate;

/**
 * @Title: PredicateNegate
 * @Package: com.sk.jdk8.predicate
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 11:38
 */
public class PredicateNegate {
    public static boolean checkString(String s, Predicate<String> predicate1) {
        //return !predicate1.test(s);
        // 等价于return !predicate1.test(s);
        return predicate1.negate().test(s);
    }

    public static void main(String[] args) {
        boolean flag = checkString("abcdef",s -> s.length()>5);
        System.out.println(flag);
    }
}
