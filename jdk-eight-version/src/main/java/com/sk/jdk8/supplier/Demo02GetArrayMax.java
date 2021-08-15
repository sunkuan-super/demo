package com.sk.jdk8.supplier;

import java.util.function.Supplier;

/**
 * @Title: Demo02GetArrayMax
 * @Package: com.sk.jdk8.supplier
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/28 - 15:36
 */
public class Demo02GetArrayMax {
    public static int getMax(Supplier<Integer> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        int[] arr = {10,76,43,99,33,-30};
        //调用getMax方法，方法的参数Supplier是一个函数时接口，所以可以传递Lambda表达式
        int maxValue = getMax(() -> {
            int max = arr[0];
            for (int i : arr) {
                if (i>=max){
                    max = i;
                }
            }
            return max;
        });
        System.out.println(maxValue);
    }
}
