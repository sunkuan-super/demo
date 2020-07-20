package com.aspirecn.jdk8.stream.terminal;

import com.aspirecn.jdk8.method_ref.Employee;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.NonNull;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Title: CollectDemo
 * @Package: com.aspirecn.jdk8.stream.terminal
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/30 - 17:43
 */
public class CollectDemo {

    private List<Employee> employeeList = Arrays.asList(
            new Employee("zhangsan", 19, 1000d),
            new Employee("lisi", 20, 2000d),
            new Employee("wangwu", 30, 3000d),
            new Employee("zhaoliu", 35, 4000d),
            new Employee("tianqi", 50, 5000d),
            new Employee("tianqi", 50, 5000d)
    );

    @Test
    public void collectToCollection() {
        System.out.println("--------------------- List ------------------------");
        /**
         * 收集到 List 中
         */
        // collect(Collector<? super T, A, R> collector)
        List<String> nameList = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(nameList);
        System.out.println("--------------------- Set ------------------------");

        /**
         *  收集到 Set 中
         */
        Set<String> nameSet = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        System.out.println(nameSet);
        System.out.println("--------------------- HashSet ------------------------");

        /**
         * 收集到 HashSet中
         */
        HashSet<String> nameHashSet = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(nameHashSet);
        System.out.println("--------------------- TreeSet ------------------------");

        /**
         * 收集到 TreeSet中
         */
        TreeSet<String> nameTreeSet = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(nameTreeSet);

        /**
         * 收集到其他数据结构同理
         */
    }

    @Test
    public void collectByAggregate() {
        System.out.println("-------------------- count -----------------------");

        /**
         * 总数 counting()
         */
        Long count = employeeList.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        System.out.println("-------------------- avg -------------------------");

        /**
         * 平均值
         */
        employeeList.stream()
                .collect(Collectors.averagingLong(
                        employee -> employee.getAge()
                ));

        //TODO averagingInt(Employee::getAge)的返回值Double
        //优化
        Double ageAvg = employeeList.stream()
                .collect(Collectors.averagingDouble(Employee::getAge));

        System.out.println(ageAvg);
        System.out.println("-------------------- sum -------------------------");

        /**
         * sum求和
         */
        Integer ageSum = employeeList.stream()
                .collect(Collectors.summingInt(Employee::getAge));
        System.out.println(ageSum);
        System.out.println("-------------------- max -------------------------");

        /**
         * age 最大的员工
         */
        Optional<Employee> maxEmployee = employeeList.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getAge(), e2.getAge())));
        System.out.println(maxEmployee.get());
        System.out.println("-------------------- min -------------------------");

        /**
         * 年龄的最小值
         */
        Optional<@NonNull Integer> minAgeOptional = employeeList.stream()
                .map(Employee::getAge)
                .collect(Collectors.minBy(Integer::compareTo));
        System.out.println(minAgeOptional.get());
    }

    @Test
    public void groupBy() {
        Map<Integer, List<Employee>> map = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getAge));

        System.out.println(map);

    }

    @Test
    public void multistageGroupBy() {
        Map<String, Map<String, List<Employee>>> stringMapMap = employeeList.stream()
                .collect(Collectors.groupingBy(employee -> {
                    if (employee.getAge() < 19) {
                        return "少年";
                    } else if (employee.getAge() < 30) {
                        return "青年";
                    } else {
                        return "老年";
                    }
                }, Collectors.groupingBy(Employee::getName)));

        System.out.println(stringMapMap);
    }

    /**
     * 分区
     */
    @Test
    public void partitionBy() {
        Map<Boolean, List<Employee>> map = employeeList.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getAge() > 19));

        System.out.println(map);
    }

    @Test
    public void summarizing(){
        DoubleSummaryStatistics dss = employeeList.stream()
                .collect(Collectors.summarizingDouble(Employee::getAge));

        System.out.println(dss.getSum());
        System.out.println(dss.getAverage());
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
    }

    @Test
    public void join() {
        String str = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "<<< ", " >>>"));

        System.out.println(str);

    }
}
