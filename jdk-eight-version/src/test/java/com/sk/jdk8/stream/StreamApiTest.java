package com.sk.jdk8.stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Title: StreamApiTest1
 * @Package: com.sk.jdk8.stream
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 21:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamApiTest {
    //创建streaming
    @Test
    public void createStream(){
        //1.可以通过Collection系列集合提供的stream()或parallelStream()
        ArrayList<String> arrayList = new ArrayList<>();
        Stream<String> stringStream =  arrayList.stream();

        //2.通过Arrays中的静态方法stream()获取数组流
        Stream<Integer> integerStream = Arrays.stream(new Integer[10]);

        //3.通过Stream 类中的静态方法of()
        Stream<String> stringStream1 = Stream.of("aaa","bbb","ccc");

        //4. 创建无限流
        //迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        // iterate.forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
    }
}
