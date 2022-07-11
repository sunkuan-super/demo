package com.sk.practice.study.collection.map.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();

        String put = hashMap.put("a", "1");
        System.out.println(put);
        // 如果对同一个key 两次调用put方法，第二个值就会取代第一个值。实际上，put将返回这个key存储的上一个value
        put = hashMap.put("a", "2");
        System.out.println(put);
        hashMap.put("b", "3");

        // 2. 删除一个元素, 返回的是key所对应的value
        String value = hashMap.remove("a");
        System.out.println(value);
        // 删除一个元素，返回的是删除的结果
        boolean b = hashMap.remove("b", "4");
        System.out.println(b);
        System.out.println(hashMap);

        // 3. 遍历Map
        hashMap.forEach((k, v) -> {
            System.out.println("key=" + k + ",value=" + v);
        });

        // 4. 获取Map的值
        String a = hashMap.getOrDefault("a", "5");
        System.out.println(a);

        // 5. 更新映射项
        Map<String, Integer> wordCountMap = new HashMap<>();
        ArrayList<String> list = new ArrayList<String>();
        list.add("java");
        list.add("spark");
        list.add("java");
        list.add("java");
        list.add("spark");
        list.add("python");

        for (String word : list) {
            // 方式一：
//            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);

            // 方式二：
//            Integer integer = wordCountMap.putIfAbsent(word, 0);
//            wordCountMap.put(word, wordCountMap.get(word) + 1);

            // 方式三
            wordCountMap.merge(word, 1, Integer::sum);
        }
        System.out.println(wordCountMap);
    }
}
