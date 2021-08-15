package com.sk.jdk8.lambda;

/**
 * @Title: Demo01Runnable
 * @Package: com.sk.jdk8.lambda
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/28 - 11:53
 */

/**
 * 例如java.lang.Runnable接口就是一个函数式接口
 * 结社有一个startThread的方法使用该接口作为参数，那么就可以使用lambda进行传参
 * 这种情况其实和Thread类的构造方法参数为Runnable没有本质区别
 */
public class Demo01Runnable {
    public static void startThread(Runnable run) {
        //开启多线程
        new Thread(run).start();
    }

    public static void main(String[] args) {
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"启动了");
            }
        });

        //调用startThread方法参数是一个函数式接口，所以可以传递Lambda表达式
        //Runnable的run方法没有参数，所以传一个()
        startThread(()->{
            System.out.println(Thread.currentThread().getName()+"启动了");
        });

        //优化
        startThread(()->System.out.println(Thread.currentThread().getName()+"启动了"));
    }



}
