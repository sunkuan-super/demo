package com.sk.practice.study.collection.map.sortedmap;

import java.util.*;

public class SortedMapTest {

    public static void main(String[] args) {
        SortedMap<String, String> map = new TreeMap<String, String>();

        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.put("d", "3");
        String firstKey = map.firstKey();
        String lastKey = map.lastKey();
        System.out.println("-------------------");
        System.out.println(firstKey);
        System.out.println("-------------------");
        System.out.println(lastKey);
        System.out.println("-------------------");

        Set<String> keys = map.keySet();
        Collection<String> values = map.values();
        Set<Map.Entry<String, String>> entries = map.entrySet();

        for (String key : keys) {
            System.out.println(key);
        }

        for (String value : values) {
            System.out.println(value);
        }



    }
}
