package com.sk.javase;


/**
 * 内部类的主要特点：
 *  内部类可以访问其所在类的属性(包括所在类的私有属性)，内部类创建自身对象，需要先创建其所在类的对象
 *
 */

/**
 * @Title: TestInner
 * @Package: com.sk.javase
 * @Description: 内部类
 * @Author: sk
 * @Date: 2021/8/30 - 10:39
 */
public class TestInner {
    public class Inner {

    }


    /**
     * 内部类的范围限定可以根据需要任意设定
     */
    private class InnerA {

    }
}
