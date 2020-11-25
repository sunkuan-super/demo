package com.practice.sk.test;

import com.practice.sk.EBusinessApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Title: TestTime
 * @Package: com.practice.sk.test
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/20 - 17:03
 */
@SpringBootTest(classes = EBusinessApplication.class)
@RunWith(SpringRunner.class)
public class TestTime {
    @Test
    public void testTime(){
        //2020-06-10 14:27:06.651

        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime localDateTime2 = LocalDateTime.parse("2020-06-10 14:27:06.651", dtf4);
        System.out.println(localDateTime2);


        LocalDateTime localDateTime = localDateTime2.now();
        boolean equal = localDateTime.isEqual(localDateTime2);
        System.out.println(equal);
    }
}
