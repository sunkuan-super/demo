package com.sk.practice.study.collection.map.hashmap;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IteratorMap {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < 100000000; i++){
            hashMap.put("" + i, "thanks");
        }

        long bs = System.currentTimeMillis();
        Iterator iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()){
            hashMap.get(iterator.next());
        }
        System.out.println();
        System.out.println(System.currentTimeMillis() - bs);

        listHashMap();
    }

    public static void listHashMap() {
        HashMap hashmap = new HashMap();
        for (int i = 0; i < 100000000; i++){
            hashmap.put("" + i, "thanks");
        }
        long bs = Calendar.getInstance().getTimeInMillis();
        Iterator it = hashmap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            // entry.getKey() 返回与此项对应的键
            // entry.getValue() 返回与此项对应的值
            entry.getValue();
        }
        System.out.println();
        System.out.println(Calendar.getInstance().getTimeInMillis() - bs);
    }
}
