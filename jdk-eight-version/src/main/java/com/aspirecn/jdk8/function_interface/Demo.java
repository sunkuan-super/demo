package com.aspirecn.jdk8.function_interface;

/**
 * @Title: Demo
 * @Package: com.aspirecn.jdk8.function
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/28 - 10:55
 */

/**
 * 函数式接口的使用：一般可以作为方法的参数和返回值类型
 */
public class Demo {
    //定义一个方法，参数使用函数式接口MyFunctionInterFace
    public static void show(MyFunctionInterface myFunctionInterface){
        myFunctionInterface.myMethod();
    }

    public static void main(String[] args) {

        //调用show方法，方法的参数是一个接口，所以可以传递接口的匿名内部类
        show(new MyFunctionInterface() {
            @Override
            public void myMethod() {
                System.out.println("使用匿名内部类重写接口中的抽象方法");
            }
        });

        //调用show方法，方法的参数是一个接口，所以可以传递接口的实现类对象
        show(new MyFunctionInterfaceImpl());

        //调用show方法,方法的参数是一个接口
        show(()->{
            System.out.println("使用b表达式重写接口中的抽象方法");
        });

        //简化Lambda表达式

   }
}
