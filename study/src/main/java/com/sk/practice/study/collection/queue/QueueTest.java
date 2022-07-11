package com.sk.practice.study.collection.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        // add
        queue.add("a");
        queue.add("b");
        queue.add("c");
        System.out.println(queue.add("d"));
        System.out.println(queue.size());
        // offer

    }
}
