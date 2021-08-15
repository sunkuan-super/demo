package com.sk.flume;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Title: TTT
 * @Package: com.sk.flume
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/9/18 - 16:55
 */
public class TTT {
    public static void main(String[] args) {
        String line = "MME gets HSS unsuccessfully No suitable cells in tracking area 不携带";
        line = line.replaceAll("[\\u4e00-\\u9fa5]{1,}", "");
        System.out.println(line);
        List<String> list = Stream.of(line.split("\\s+")).filter(o -> o.matches("^[A-Z]+.*")).collect(Collectors.toList());
        for (String s : list) {
            System.out.println(s);
        }
        //        int lastIndexOf = line.lastIndexOf(list.get(list.size() - 1));
//        String substring = line.substring(lastIndexOf);
//        System.out.println(substring);
    }
}
