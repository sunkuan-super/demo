package com.aspirecn.jdk8.stream;

import com.aspirecn.jdk8.method_ref.Employee;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @Title: MapAndFlatMapDemo
 * @Package: com.aspirecn.jdk8.stream
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/30 - 11:04
 */
public class MapAndFlatMapDemo {

    private List<Employee> employeeList = Arrays.asList(
            new Employee("zhangsan", 19, 1000d),
            new Employee("lisi", 20, 2000d),
            new Employee("wangwu", 30, 3000d),
            new Employee("zhaoliu", 35, 4000d),
            new Employee("tianqi", 50, 5000d)
    );

    @Test
    public void map(){
        employeeList.stream()
                .map(employee -> employee.getName())
                .forEach(System.out::println);

        System.out.println();

        // 优化
        /**
         * map里需要一个Function类型的函数式接口，传入一个类型，返回一个类型
         *
         * Lambda参数列表中，第一个参数是
         *
         */
        employeeList.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println();

        Function<Employee, Double> function = (employee) -> employee.getSalary();
        for (Employee employee : employeeList) {
            Double d = function.apply(employee);
            System.out.println(d);
        }

    }

    @Test
    public void flatMap(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd");

        Stream<Stream<Character>> streamStream = list.stream()
                .map(MapAndFlatMapDemo::getCharacterStream);

        streamStream.forEach(sm -> sm.forEach(System.out::println));

        System.out.println();

        /**
         * 用flatMap替代
         */
        list.stream()
                .flatMap(MapAndFlatMapDemo::getCharacterStream)
                .forEach(System.out::println);

    }

    public static Stream<Character> getCharacterStream(String s) {
        char[] chars = s.toCharArray();
        Character[] characters = ArrayUtils.toObject(chars);
        return Arrays.stream(characters);

    }

}
