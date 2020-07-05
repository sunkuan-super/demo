package com.aspirecn.mybatis.domain;

import lombok.Data;

/**
 * @Title: User
 * @Package: com.example.mybatis.domain
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/23 - 11:44
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setUsername("11");
        user.setPassword("111");

        System.out.println(user);
    }
}
