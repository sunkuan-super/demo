package com.sk.javase.bean;

/**
 * @Title: StaticInner
 * @Package: com.sk.javase.bean
 * @Description:
 * @Author: sk
 * @Date: 2021/8/30 - 11:17
 */
public class StaticInner {

    /**
     * 定义static内部类
     */
    public static class Inner2 {
        // static内部类可以定义static元素
        public static void paint(){
            System.out.println("inner");
        }
    }

    public static void main(String[] args) {
        StaticInner.Inner2 in = new StaticInner.Inner2();
        in.paint();

        // 也可以直接类名.去调用
        Inner2.paint();
    }
}
