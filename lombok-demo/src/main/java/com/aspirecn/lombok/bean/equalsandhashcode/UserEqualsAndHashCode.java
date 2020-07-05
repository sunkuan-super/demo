package com.aspirecn.lombok.bean.equalsandhashcode;

import com.aspirecn.lombok.bean.tostring.UserToString;
import lombok.*;

/**
 * @Title: UserEqualsAndHashCode
 * @Package: com.aspirecn.lombok.bean.equalsandhashcode
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 15:02
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id"},of = {"name","age"})
public class UserEqualsAndHashCode {
    private Integer id;

    private String name;

    private Integer age;

//    public static void main(String[] args) {
//        UserEqualsAndHashCode userEqualsAndHashCode = new UserEqualsAndHashCode();
//        System.out.println(userEqualsAndHashCode);
//    }
}
