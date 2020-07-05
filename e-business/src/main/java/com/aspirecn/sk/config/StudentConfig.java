package com.aspirecn.sk.config;

import com.aspirecn.sk.bean.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * @Title: PersonConfig
 * @Package: com.aspirecn.sk.config
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/22 - 13:48
 */
@Configuration
@ConfigurationProperties(prefix = "student")
@Data
public class StudentConfig {
    private Integer id;

    private String name;

    private Integer age;
}
