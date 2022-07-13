package com.sk.leetcode.demo.datastructure;

import java.util.*;

public class QueueTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>(100);

        queue.add(111);
        queue.add(222);
        queue.add(333);

        queue.iterator().forEachRemaining(ele -> System.out.println(ele));
        Collection collection = new ArrayList();

        Iterator iterator = collection.iterator();
        iterator.hasNext();
        iterator.next();


    }
}
