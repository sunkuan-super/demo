package com.sk.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: create by sunkuan
 * @Description:
 * @date: 2022/7/10 - 11:59
 */
public class SimpleDateFormatThreadLocal {

    //private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 执行总次数
     */
    private static final int EXECUTE_COUNT = 1000;

    /**
     * 同时运行的线程数量
     */
    private static final int THREAD_COUNT = 20;

    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(EXECUTE_COUNT);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    SimpleDateFormat SDF = THREAD_LOCAL.get();
                    SDF.parse("2022-07-10");
                } catch (InterruptedException e) {
                    System.out.println("获取信号量出错");
                    e.printStackTrace();
                    System.exit(1);
                } catch (ParseException e) {
                    System.out.println("ParseException异常--线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                    e.printStackTrace();
                    System.exit(1);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException异常---线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                    e.printStackTrace();
                    System.exit(1);
                }

                semaphore.release();
                latch.countDown();
            });


        }

        latch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");
    }


}
