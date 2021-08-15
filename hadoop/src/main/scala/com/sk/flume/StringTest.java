package com.sk.flume;

/**
 * @Title: StringTest
 * @Package: com.sk.flume
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/9/16 - 10:27
 */
public class StringTest {
    public static void main(String[] args) {
//        String str = "aa|   aa";
//        System.out.println(str.contains(" "));
//
//        System.out.println(str.split(" ").length);
//        for (String s : str.split(" ")) {
//            System.out.println(s);
//        }
//        System.out.println(str.split("|").length);
//        for (String s : str.split("\\|")) {
//            System.out.println(s);
//        }

        String str = "=aa=bbb=ccc";
        System.out.println(str.split("=")[0]);
        System.out.println(str.split("=")[1]);
    }
}
