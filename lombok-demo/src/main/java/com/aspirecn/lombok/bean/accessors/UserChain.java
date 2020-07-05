package com.aspirecn.lombok.bean.accessors;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Title: UserChain
 * @Package: com.aspirecn.lombok.bean.chain
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 16:17
 */
@Data
@Accessors(chain = true)
public class UserChain {
    private  Integer id;

    private String name;

    private Integer age;

    public static void main(String[] args) {
        UserChain userChain = new UserChain();
        userChain.setId(1).setAge(10).setName("张三");
        System.out.println(userChain);
    }

}
