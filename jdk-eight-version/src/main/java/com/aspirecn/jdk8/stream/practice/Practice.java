package com.aspirecn.jdk8.stream.practice;

import com.aspirecn.jdk8.method_ref.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Title: Practice
 * @Package: com.aspirecn.jdk8.stream.practice
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/7/1 - 10:45
 */
public class Practice {
    private List<Employee> employeeList = Arrays.asList(
            new Employee("zhangsan", 19, 1000d),
            new Employee("lisi", 20, 2000d),
            new Employee("wangwu", 30, 3000d),
            new Employee("zhaoliu", 35, 4000d),
            new Employee("tianqi", 50, 5000d)
    );

    @Test
    public void map_reduce(){
       Integer integer = employeeList.stream()
               .map((employee) -> 1)
               .reduce(0,(x,y) -> x+y);

        System.out.println(integer);

        //优化
        Optional<Integer> reduce = employeeList.stream()
                .map(employee -> 1)
                .reduce(Integer::sum);

        System.out.println();
    }
}
