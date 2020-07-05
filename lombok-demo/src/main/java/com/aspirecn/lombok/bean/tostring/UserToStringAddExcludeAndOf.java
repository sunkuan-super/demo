package com.aspirecn.lombok.bean.tostring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Title: UserToStringAddExcludeAndOf
 * @Package: com.aspirecn.lombok.bean.tostring
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 14:56
 */
@Getter
@Setter
@ToString(exclude = {"id"},of = {"name"})
public class UserToStringAddExcludeAndOf {
    private Integer id;

    private String name;

    private Integer age;

    public static void main(String[] args) {
        System.out.println(new UserToStringAddExcludeAndOf());
    }
}
