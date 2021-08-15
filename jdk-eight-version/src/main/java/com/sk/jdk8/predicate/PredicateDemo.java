package com.sk.jdk8.predicate;

/**
 * @Title: PredicateDemo
 * @Package: com.sk.jdk8.predicate
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 11:53
 */

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 练习：集合信息筛查
 * 数组当中有多条"姓名+性别"的信息如下
 * String[] array = { "迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女" };
 * <p>
 * 请通过Predicate 接口的拼装将符合要求的字符串筛选到集合ArrayList 中，需要同时满足两个条件：
 * 1. 必须为女生；
 * 2. 姓名为4个字。
 * <p>
 * 分析：
 * 1.有两个判断条件，所以需要使用两个Predicate接口，对条件进行判断
 * 2.必须同事满足两个条件，所以可以使用and方法连接两个判断
 */
public class PredicateDemo {
    public static List<String> filter(String[] arr, Predicate<String> predicate1, Predicate<String> predicate2) {
        List<String> list = new ArrayList<>();
        for (String s : arr) {
            boolean result = predicate1.and(predicate2).test(s);
            if (result) {
                list.add(s);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String[] array = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女"};
        List<String> stringList = filter(array, (string) -> {
            return "女".equals(string.split(",")[1]);
        }, (string) -> {
            return string.split(",")[0].length() == 4;
        });

        for (String s : stringList) {
            System.out.println(s);
        }

    }

}
