package com.sk.practice.study.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Title: ListDemo
 * @Package: com.sk.practice.study.collection
 * @Description:
 * @Author: sk
 * @Date: 2022/7/27 - 16:28
 */
public class ListDemo {
    public static void main(String[] args) {
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list2.add(i);
        }

        System.out.println(list2.hashCode());


        Iterator<Integer> iterator = list2.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next > 5){
                iterator.remove();
            }
        }

        System.out.println(list2);
        System.out.println(list2.hashCode());

        list2.add(4444);
        System.out.println(list2.hashCode());
    }
}
