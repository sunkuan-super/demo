package com.sk.practice.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Title: ImplementsCallable
 * @Package: com.sk.practice.study.thread
 * @Description:
 * @Author: sk
 * @Date: 2022/8/1 - 13:58
 */
public class ImplementsCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 1000; i++) {
            System.out.println("子线程：" + i);
        }
        return "success";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ImplementsCallable callable = new ImplementsCallable();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();

        ImplementsCallable22 callable2 = new ImplementsCallable22();
        FutureTask<String> futureTask2 = new FutureTask<>(callable2);
        Thread thread2 = new Thread(futureTask2);
        thread2.start();

        System.out.println(futureTask.get());
        System.out.println(futureTask2.get());

        for (int i = 0; i < 1000; i++) {
            System.out.println("主线程-------" + i);
        }
    }

    static class ImplementsCallable22 implements Callable<String>{

        @Override
        public String call() throws Exception {
            for (int i = 0; i < 1000; i++) {
                System.out.println("" + i);
            }
            return "成功";
        }
    }
}
