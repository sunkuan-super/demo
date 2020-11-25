package com.sk.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: Student
 * @Package: com.sk.jdk8
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 14:48
 */
@Data
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
}
