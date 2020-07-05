package com.aspirecn.jdk8.predicate;

import java.util.function.Predicate;

/**
 * @Title: PredicateOrMethod
 * @Package: com.aspirecn.jdk8.predicate
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 11:03
 */
public class PredicateOrMethod {
    /**
     * 定义一个方法，方法的参数，传递一个字符串
     *  传递两个Predicate接口
     *      一个用于判断字符串的长度是否大于5
     *      另一个用于判断字符串中是否包含a
     *  只要有一个满足条件就可以
     */
    public static boolean checkString(String s, Predicate<String> predicate1, Predicate<String> predicate2) {
        //return predicate1.test(s) || predicate2.test(s);
        // 等价于return predicate1.test(s) || predicate2.test(s);
        return predicate1.or(predicate2).test(s);
    }

    public static void main(String[] args) {
        boolean flag = checkString("abcdef",s -> s.length()>5,s -> !s.contains("a") );
        System.out.println(flag);
    }

}
