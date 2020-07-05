package com.aspirecn.lombok.bean.getandset;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Title: User
 * @Package: com.aspirecn.lombok.bean
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 14:24
 */
@ToString
public class User {
    // 设置生成get方法的访问控制类型
    @Setter
    @Getter(AccessLevel.PRIVATE)
    private Integer id;

    private String name;

    private Integer age;

    // 不生成set方法和get方法
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private String email;

    static String string;

    final int idx = 2;

    public static void main(String[] args) {
        User user = new User();
        System.out.println(user);
    }
}
