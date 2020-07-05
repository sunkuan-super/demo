package com.aspirecn.jdk8.optional;

import com.aspirecn.jdk8.method_ref.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @Title: OptionalDemo
 * @Package: com.aspirecn.jdk8.optional
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/1 - 15:03
 */
public class OptionalDemo {

    @Test
    public void of(){
        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();
        System.out.println(employee);

        //如果构建一个空的实例，直接在构建Optional时就报空指针
        Optional<Employee> op2 = Optional.of(null);
    }

    @Test
    public void empty(){
        Optional<Employee> op = Optional.empty();

        //java.util.NoSuchElementException: No value present 直接报这个错，能更好地定位问题所在
        System.out.println(op.get());
    }

    @Test
    public void ofNullable(){
        Optional<Employee> op = Optional.ofNullable(null);

        //java.util.NoSuchElementException: No value present 直接报这个错，能更好地定位问题所在
        System.out.println(op.get());
    }

    @Test
    public void isPresent(){
        Optional<Employee> op = Optional.ofNullable(null);

        //如果有值，则输出
        if(op.isPresent()) {
            System.out.println(op.get());
        }
    }

    @Test
    public void orElse(){
        Optional<Employee> op = Optional.ofNullable(null);
        Employee employee = op.orElse(new Employee("zhangsan",15,22222d));
        System.out.println(employee);
    }

    @Test
    public void orElseGet(){
        Optional<Employee> op = Optional.ofNullable(null);
        Employee employee = op.orElseGet(() -> new Employee("zhangsan",15,22222d));
        System.out.println(employee);
    }

    @Test
    public void map(){
        Optional<Employee> op = Optional.ofNullable(new Employee("zhangsan",15,22222d));
        //public<U> Optional<U> map(Function<? super T, ? extends U> mapper)
        Optional<String> optional = op.map(employee -> employee.getName());

        System.out.println(optional.get());
    }

    @Test
    public void flatMap(){
        Optional<Employee> op = Optional.ofNullable(new Employee("zhangsan",15,22222d));

        //public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)   返回值必须是一个Optional
        Optional<String> stringOptional = op.flatMap(employee -> Optional.of(employee.getName()));
        System.out.println(stringOptional.get());
    }

}
