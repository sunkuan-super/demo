package com.aspirecn.lombok.bean.accessors;

import com.aspirecn.lombok.bean.getandset.User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Title: UserFluent
 * @Package: com.aspirecn.lombok.bean.accessors
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 16:24
 */
@Data
@Accessors(fluent = true)
public class UserFluent {
    private Long id;
    private String name;

    public static void main(String[] args) {
        UserFluent userFluent = new UserFluent();

        UserFluent userFluent1 = userFluent.id(1L).name("张三");

        System.out.println(userFluent == userFluent1);



    }
}
