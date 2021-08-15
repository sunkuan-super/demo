package com.sk.jdk8.stream.terminal;

/**
 * @Title: TerminalDemo
 * @Package: com.sk.jdk8.stream
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/30 - 15:14
 */

import com.sk.jdk8.method_ref.Employee;
import lombok.NonNull;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
    查找与匹配
    allMatch  ---检查是否匹配所有元素      （必须全部都满足才会返回true）
    anyMatch  ---检查是否至少匹配一个元素  （只要有一个条件满足即返回true）
    noneMatch ---检查是否没有匹配所有元素  （全都不满足才会返回true）
    findFirst ---返回第一个元素
    findAny   ---返回当前流中的任意元素
    count     ---返回流中元素的总个数
    max       ---返回流中的最大值
    min       ---返回流中的最小值

 */

public class TerminalDemo {

    private List<Employee> employeeList = Arrays.asList(
            new Employee("zhangsan", 19, 1000d),
            new Employee("lisi", 20, 2000d),
            new Employee("wangwu", 30, 3000d),
            new Employee("zhaoliu", 35, 4000d),
            new Employee("tianqi", 50, 5000d)
    );

    @Test
    public void allMatch(){
        boolean b = employeeList.stream()
                .allMatch(employee -> employee.getAge() > 0);
        //全部都满足才会返回true
        System.out.println("allMatch: "+b);
    }

    @Test
    public void anyMatch(){
        boolean b = employeeList.stream()
                .anyMatch(employee -> employee.getAge() > 50);
        //只要有一个条件满足即返回true
        System.out.println("allMatch: "+b);
    }

    @Test
    public void noneMatch(){
        boolean b = employeeList.stream()
                .noneMatch(employee -> employee.getAge() > 6);
        //全都不满足才会返回true
        System.out.println("noneMatch: " + b);
    }

    @Test
    public void findFirst(){
        Optional<Employee> optionalEmployee = employeeList.stream()
                .filter(employee -> employee.getAge()>18)
                .findFirst();

        Employee employee = optionalEmployee.get();
        System.out.println(employee);
    }

    @Test
    public void findAny(){
        Optional<Employee> optionalEmployee = employeeList.parallelStream()
                .filter(employee -> employee.getAge() > 18)
                .findAny();

        Employee employee = optionalEmployee.get();
        System.out.println(employee);
    }

    @Test
    public void count(){
        long count = employeeList.stream()
                .filter(employee -> employee.getAge() > 18)
                .count();

        System.out.println(count);

        /**
         * 运行结果： 5
         */
    }

    @Test
    public void max(){
        Optional<@NonNull Integer> maxOption = employeeList.stream()
                .map(employee -> employee.getAge())
                .max((age1, age2) -> age1.compareTo(age2));

        //优化
        employeeList.stream()
                .map(employee -> employee.getAge())
                .max(Double::compare);

        //再优化
        employeeList.stream()
                .map(Employee::getAge)
                .max(Double::compare);

        System.out.println(maxOption.get());
    }

    @Test
    public void min(){
        Optional<Integer> minOptional = employeeList.stream()
                .map(employee -> employee.getAge())
                .min((age1,age2) -> Double.compare(age1,age2));

        //优化
        employeeList.stream()
                .map(employee -> employee.getAge())
                .min(Double::compare);

        System.out.println(minOptional.get());
    }
}
