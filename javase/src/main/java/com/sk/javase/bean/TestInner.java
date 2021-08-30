package com.sk.javase.bean;


/**
 * 内部类的主要特点：
 * 内部类可以访问其所在类的属性(包括所在类的私有属性)，内部类创建自身对象，需要先创建其所在类的对象
 */

/**
 * @Title: TestInner
 * @Package: com.sk.javase
 * @Description: 内部类
 * @Author: sk
 * @Date: 2021/8/30 - 10:39
 */
public class TestInner {

    private int number = 100;

    public class Inner {
        private int number = 200;

        public void paint() {
            int number = 500;
            // 局部覆盖原则
            System.out.println(number);

            // 通过this引用内部类的成员属性
            System.out.println(this.number);

            // 通过外部类名加this的方式访问外部类成员属性
            System.out.println(TestInner.this.number);
        }

    }

    public static void main(String[] args) {
        // 创建内部类对象分为两个步骤
        TestInner inner = new TestInner();

        TestInner.Inner in = inner.new Inner();
        in.paint();
    }
}
