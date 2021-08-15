package com.sk.jdk8;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Title: CollectionDemo
 * @Package: com.sk.jdk8
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 14:42
 */
public class CollectionDemo {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list = Arrays.asList(new Student(1,"zhangsan"),new Student(1,"lisi"),new Student(2,"wangwu"));
        Collections.sort(list,(o1,o2)->{
            if(o1.getId().equals(o2.getId())){
                return o1.getName().compareTo(o2.getName());
            }else {
                return -Integer.compare(o1.getId(),o2.getId());
            }

        });

        for (Student student : list) {
            System.out.println(student);
        }
    }
}
