package com.aspirecn.demo.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Title: User
 * @Package: com.aspirecn.demo.domain
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/23 - 14:07
 */
@Data
@Entity
//@Table(name = "user1")
public class User {
    @Id
    @GeneratedValue
//    @Column(name = "id")
    private Integer id;

    //    @Column(name = "name")
    private String name;

    private String password;
}
