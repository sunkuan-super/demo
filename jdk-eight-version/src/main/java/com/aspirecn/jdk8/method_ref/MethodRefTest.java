package com.aspirecn.jdk8.method_ref;

import lombok.ToString;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * @Title: MethodRefTest
 * @Package: com.aspirecn.jdk8.method_ref
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 15:48
 */
public class MethodRefTest {

    // 对象:: 实例方法名
    @Test
    public void test() {
        Consumer<String> con = string -> System.out.println(string);
        con.accept("hehe");

        /*
            public void println(String x) {
                synchronized (this) {
                    print(x);
                    newLine();
                }
            }
         */
        // System.out 对象的println方法的参数为1个返回值为void,与accept方法保持一致
        Consumer<String> con1 = System.out::println;
        con1.accept("heihei");

        Employee employee = new Employee(13, 20D);
        Supplier<Integer> supplier = () -> employee.getAge();
        Integer integer = supplier.get();
        System.out.println(integer);

        /**
         * employee的getAge方法与Supplier()的参数和返回值保持一致
         */
        Supplier<Integer> supplier1 = employee::getAge;
        Integer integer1 = supplier1.get();
        System.out.println(integer1);
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        /**
         * compare的参数列表与返回值类型
         * 和
         * Comparator的compare的参数列表和返回值一致
         */
        Comparator<Integer> com1 = Integer::compare;

    }

    /**
     * 类::实例方法名
     */
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String,String> bp1 = String::equals;
    }

    /**
     * 构造器引用 （无参）
     * className::new
     */
    @Test
    public void test5(){
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();
        System.out.println(employee);

        /**
         *  需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
         *  此函数式接口没有参数,则 Employee::new会默认调用无参构造方法
         */
        Supplier<Employee> supplier1 = Employee::new;
        Employee employee1 = supplier.get();
        System.out.println(employee1);
    }

    /**
     * 构造器引用 （1参）
     * className::new
     */
    @Test
    public void test6(){
        Function<Integer,Employee> function = (x)->new Employee(x);
        System.out.println(function.apply(12));

        /**
         * 需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
         * 则函数式接口只有一个参数，则Employee::new默认会调用一个参数的构造方法
         */
        Function<Integer,Employee> function1 = Employee::new;
        System.out.println(function1.apply(12));

    }

    /**
     * 构造器引用 （多参）
     * className::new
     */
    @Test
    public void test7(){
        /**
         * 需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
         * 则函数式接口有两个参数，则Employee::new默认会调用两个参数的构造方法
         */
        BiFunction<Integer,Double,Employee> biFunction = (age,salary) -> new Employee(age,salary);
        BiFunction<Integer,Double,Employee> biFunction1 = Employee::new;
        Employee apply = biFunction1.apply(20, 12000d);
        System.out.println(apply);
    }

    /**
     * 数组引用
     * Type[]::new
     */
    @Test
    public void test8(){
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] strings1 = function.apply(10);
        System.out.println(strings1.length);

        Function<Integer, String[]> function1 = String[]::new;
        String[] strings2 = function1.apply(20);
        System.out.println(strings2.length);
    }
}
