package com.aspirecn.jdk8.stream.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @Title: ForkJoinTest
 * @Package: com.aspirecn.jdk8.stream.forkjoin
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/1 - 14:43
 */
public class ForkJoinTest {

    @Test
    public void testForkJoin() {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinCalculate(0, 100000000L);

        Long sum = pool.invoke(forkJoinTask);
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("时间: " + Duration.between(start, end).toMillis());
    }

    @Test
    public void testFor() {
        Instant start = Instant.now();

        long sum = 0L;
        for (long i = 0; i <= 100000000L; i++) {
            sum += i;
        }

        System.out.println(sum);
        Instant end = Instant.now();

        System.out.println("时间: " + Duration.between(start, end).toMillis());
    }

    @Test
    public void paraStream(){
        Long reduce = LongStream.rangeClosed(0, 100000000L)
                .parallel()
                .reduce(0,Long::sum);

        System.out.println(reduce);
    }
}
