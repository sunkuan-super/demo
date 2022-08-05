package com.sk.practice.study.thread;

/**
 * @Title: ExtendThread
 * @Package: com.sk.practice.study.thread
 * @Description: 创建线程的第一种方式，继承Thread类，并重写run方法。
 *               在主方法中创建此类的对象，并调用start方法
 * @Author: sk
 * @Date: 2022/8/1 - 11:46
 */
public class ExtendsThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 1000; i++) {
            System.out.println("子线程：" + i);
        }
    }

    public static void main(String[] args) {
        ExtendsThread thread = new ExtendsThread();
        thread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("主线程-------" + i);
        }
    }
}
