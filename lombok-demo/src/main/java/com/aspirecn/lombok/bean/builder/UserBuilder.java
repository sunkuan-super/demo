package com.aspirecn.lombok.bean.builder;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.val;

import java.util.HashMap;

/**
 * @Title: UserBuilder
 * @Package: com.aspirecn.lombok.bean.builder
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 15:40
 */
@Data
@Builder
public class UserBuilder {
    private final Integer id;

    @NonNull private String name;

    private Integer age;

    public static void main(String[] args) {
        UserBuilder userBuilder= UserBuilder.builder().id(1).name("zhangsan").age(12).build();
        System.out.println(userBuilder);
        val map = new HashMap<>(10);
        map.put("java","江涛");
        System.out.println(map);
    }
}
