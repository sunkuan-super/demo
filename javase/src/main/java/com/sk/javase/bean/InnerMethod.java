package com.sk.javase.bean;

/**
 * @Title: InnerMethod
 * @Package: com.sk.javase.bean
 * @Description:
 * @Author: sk
 * @Date: 2021/8/30 - 11:06
 */
public class InnerMethod {
    public class Inner{
        public Car getCar(){
            // 方法体内的内部类
            class BMW extends Car{
                @Override
                public void paint() {
                    super.paint();
                    System.out.println("BMW");
                }
            }

            return new BMW();
        }
    }

    public static void main(String[] args) {
        // 注意，创建内部类对象分为两个步骤
        InnerMethod innerMethod = new InnerMethod();

        InnerMethod.Inner in = innerMethod.new Inner();

        in.getCar().paint();

    }
}

class Car{
    public void paint(){
        System.out.println("car");
    }
}
