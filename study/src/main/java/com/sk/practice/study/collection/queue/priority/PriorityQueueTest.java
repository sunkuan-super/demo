package com.sk.practice.study.collection.queue.priority;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));

        // 并不是像TreeSet一样，迭代时候有序
        System.out.println("Iterating over elements....");
        for (LocalDate localDate : pq) {
            System.out.println(localDate);
        }

        // 只有在remove的时候才是有序的
        System.out.println("Removing elements...");
        while (!pq.isEmpty()){
            System.out.println(pq.remove());
        }
    }
}
