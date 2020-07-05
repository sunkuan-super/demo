package com.aspirecn.sk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Title: com.aspirecn.sk.EBusinessApplication
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/12 - 9:47
 */
@SpringBootApplication
@EnableSwagger2
public class EBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(EBusinessApplication.class, args);
    }
}
