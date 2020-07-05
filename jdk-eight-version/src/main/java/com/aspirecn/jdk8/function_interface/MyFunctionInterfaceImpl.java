package com.aspirecn.jdk8.function_interface;

/**
 * @Title: MyFunctionInterfaceImpl
 * @Package: com.aspirecn.jdk8.function
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/28 - 11:02
 */
public class MyFunctionInterfaceImpl implements MyFunctionInterface {
    @Override
    public void myMethod() {
        System.out.println("实现了抽象类");
    }
}
