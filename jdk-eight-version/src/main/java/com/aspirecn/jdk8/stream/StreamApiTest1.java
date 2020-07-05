package com.aspirecn.jdk8.stream;

import com.aspirecn.jdk8.method_ref.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @Title: StreamApiTest1
 * @Package: com.aspirecn.jdk8.stream
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/24 - 21:01
 */
@Slf4j
public class StreamApiTest1 {

    private List<Employee> employeeList = Arrays.asList(
            new Employee("zhangsan", 19, 1000d),
            new Employee("lisi", 20, 2000d),
            new Employee("wangwu", 30, 3000d),
            new Employee("zhaoliu", 35, 4000d),
            new Employee("tianqi", 50, 5000d)
    );


    //创建streaming
    @Test
    public void createStream() {
        //1.可以通过Collection系列集合提供的stream()或parallelStream()
        ArrayList<String> arrayList = new ArrayList<>();
        Stream<String> stringStream = arrayList.stream();

        //2.通过Arrays中的静态方法stream()获取数组流
        Stream<Integer> integerStream = Arrays.stream(new Integer[10]);

        //3.通过Stream 类中的静态方法of()
        Stream<String> stringStream1 = Stream.of("aaa", "bbb", "ccc");

        //4. 创建无限流
        //迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        // iterate.forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
    }

    @Test
    public void filter() {
        //中间操作，不会执行任何操作
        Stream<Employee> employeeStream = employeeList.stream()
                .filter((employee) -> {
            System.out.println("Stream API的中间操作");
            return employee.getAge() > 20;
        });

        //终止操作，一次性执行全部内容，即 "惰性求值"
        employeeStream.forEach(System.out::println);
    }

    @Test
    public void limit() {
        employeeList.stream()
                .filter(employee -> {
                    System.out.println("短路");      //有点类似短路与 && 短路或 ||
                    return employee.getAge() > 20;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void skip(){
        employeeList.stream()
                .filter(employee -> employee.getAge()>20)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void distinct(){
        employeeList.stream()
                .distinct()
                .forEach(System.out::println);
    }

}
