package com.sk.lombok.bean.accessors;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Title: UserPrefix
 * @Package: com.practice.lombok.bean.accessors
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 16:35
 */
@Getter
@Setter
@Accessors(prefix = "p")
public class UserPrefix {
    private  Integer pId;

    private String pName;

    private Integer pAge;

    public static void main(String[] args) {
        UserPrefix userPrefix = new UserPrefix();
        userPrefix.setId(1);
        userPrefix.setName("lisi");
        userPrefix.setAge(18);
    }
}
