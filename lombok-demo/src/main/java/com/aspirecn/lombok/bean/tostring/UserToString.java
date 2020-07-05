package com.aspirecn.lombok.bean.tostring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Title: UserToString
 * @Package: com.aspirecn.lombok.bean
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 14:51
 */
@Getter
@Setter
@ToString
public class UserToString {
    private Integer id;

    private String name;

    private Integer age;

    public static void main(String[] args) {
        UserToString userToString = new UserToString();
        System.out.println(userToString);
    }
}
