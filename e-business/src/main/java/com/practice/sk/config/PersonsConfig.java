package com.practice.sk.config;

import com.practice.sk.bean.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Title: PersonsConfig
 * @Package: com.practice.sk.config
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/22 - 17:36
 */
@Configuration
@ConfigurationProperties(prefix = "person")
@Data
public class PersonsConfig {
    private List<Student> persons1;
}
