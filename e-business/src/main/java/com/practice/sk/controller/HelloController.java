package com.practice.sk.controller;

import com.practice.sk.config.CityConfig;
import com.practice.sk.config.PersonsConfig;
import com.practice.sk.config.StudentConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: HelloController
 * @Package: com.aspirecn.sk.controller
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/22 - 11:23
 */
@Api(value = "任务小区接口",tags = {"任务小区接口"})
@Controller
@ResponseBody
public class HelloController {
    @Autowired
    private StudentConfig studentConfig;

    @Autowired
    private PersonsConfig personsConfig;

    @Autowired
    private CityConfig cityConfig;

    @Value("${name}")
    private String name;

    @ApiOperation(value = "查询任务小区分业务列表")
    @GetMapping("hello")
    public String hello(){
        System.out.println(name);
        System.out.println(cityConfig.getCity());
        System.out.println(studentConfig.getName());
        System.out.println(personsConfig.getPersons1());
        System.out.println(Integer.toHexString(15));

        return "hello 222";
    }
}
