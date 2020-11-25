package com.sk.jdk8.stream.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Title: ForkJoinCalculate
 * @Package: com.sk.jdk8.stream.forkjoin
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/1 - 14:20
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {
    private long start;
    private long end;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    private static final long THRESHOLD = 10000L;

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {
            long middle = (start + end)/2;

            ForkJoinCalculate left = new ForkJoinCalculate(start,middle);
            left.fork();   //拆分子任务，同时压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
