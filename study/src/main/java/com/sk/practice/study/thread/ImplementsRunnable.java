package com.sk.practice.study.thread;

/**
 * @Title: ImplementRunnable
 * @Package: com.sk.practice.study.thread
 * @Description: 自定义类实现Runnable接口并重写run()方法，
 *               创建该类的对象作为实参来构造Thread类的对象，然后使用Thread类对象调用start()方法
 * @Author: sk
 * @Date: 2022/8/1 - 13:51
 */
public class ImplementsRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("子线程：" + i);
        }
    }

    public static void main(String[] args) {
        ImplementsRunnable runnable = new ImplementsRunnable();
        Thread thread = new Thread(runnable);

        thread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("主线程-------" + i);
        }
    }
}
