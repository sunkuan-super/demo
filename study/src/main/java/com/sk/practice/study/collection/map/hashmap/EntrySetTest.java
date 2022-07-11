package com.sk.practice.study.collection.map.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EntrySetTest {

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "1");
        hashMap.put("b", "2");
        hashMap.put("c", "3");

        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            System.out.println(key);
            String value = entry.getValue();
            System.out.println(value);
            System.out.println("-----------");
        }

        // 键集视图
        boolean a = entries.remove("a");
        System.out.println(a);

        System.out.println(hashMap);


    }
}
