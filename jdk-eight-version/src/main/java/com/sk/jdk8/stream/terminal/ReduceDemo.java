package com.sk.jdk8.stream.terminal;

import com.sk.jdk8.method_ref.Employee;
import lombok.NonNull;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Title: ReduceDemo
 * @Package: com.sk.jdk8.stream.terminal
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/30 - 17:22
 */
public class ReduceDemo {

    private List<Employee> employeeList = Arrays.asList(
            new Employee("zhangsan", 19, 1000d),
            new Employee("lisi", 20, 2000d),
            new Employee("wangwu", 30, 3000d),
            new Employee("zhaoliu", 35, 4000d),
            new Employee("tianqi", 50, 5000d)
    );

    @Test
    public void reduce() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 45, 6, 7, 8, 9, 87);
        Integer reduce = integerList.stream()
                .reduce(0, (x, y) -> x + y);

        //0是起始值,也可以设置为其他值,x指的是每一次求和后的结果,y指的是集合中的每一个元素
        System.out.println(reduce);
    }

    @Test
    public void reduceEmployee() {
        @NonNull Integer reduce = employeeList.stream()
                .map(Employee::getAge)
                .reduce(0, (x, y) -> x + y);

        System.out.println(reduce);  //154

        /**
         * 这个reduce返回的是Optional类型,有可能为空，避免空指针所以设为Optional类型
         * 而上边的reduce返回的是Integer类型，上边的reduce有一个初始值，所以能返Integer
         */

        //优化
        Optional<@NonNull Integer> reduce1 = employeeList.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum);
        System.out.println(reduce1.get());
    }
}
