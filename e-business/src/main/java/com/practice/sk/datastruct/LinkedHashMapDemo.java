package com.practice.sk.datastruct;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title: LinkedHashMapDemo
 * @Package: com.practice.sk.datastruct
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/8/24 - 14:51
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        Map<String, String> hashMap = new HashMap<>(16);

        hashMap.put("aa","111");
        hashMap.put("aa","222");

        System.out.println(hashMap);

        map.put("aaa","1111");
        map.put("aaa","2222");

        System.out.println(map);

    }
}
