package com.practice.sk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title: com.practice.sk.LoggerTest
 * @Package: com.practice.sk
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/11 - 17:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1() {
        log.info("haha");
    }


}
