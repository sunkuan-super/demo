package com.sk.jdk8.stream;

import com.sk.jdk8.method_ref.Employee;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Title: soreDemo
 * @Package: com.sk.jdk8.stream
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/30 - 13:22
 */
@Slf4j
public class SortedDemo {
    private List<Employee> employeeList = Arrays.asList(
            new Employee("zhangsan", 19, 1000d),
            new Employee("lisi", 20, 2000d),
            new Employee("wangwu", 30, 3000d),
            new Employee("zhaoliu", 35, 4000d),
            new Employee("tianqi", 50, 5000d)
    );

    /**
     * 排序
     * sorted() -自然排序(Comparable)
     * sorted(Comparator comparator) -定制排序(Comparator)sorted(Comparator comparator) -定制排序(Comparator)
     */
    @Test
    public void sorted() {
        //sorted()默认是自然排序，即Comparable
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        /**
         * 定制排序(Comparator)
         */
        employeeList.stream()
                .sorted((employee1, employee2) -> {
                    if (employee1.getAge().equals(employee2.getAge())) {
                        return employee1.getName().compareTo(employee2.getName());
                    } else {
                        return employee1.getAge().compareTo(employee2.getAge());
                    }
                })
                .forEach(System.out::println);
    }

    //todo
    @Test
    public void testMap(){
        Map<String,Object> map = new HashMap<>(10);
        map.put("name","zhangsan");
        map.put("age",23);
        map.put("salary",2000d);

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
           // System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println();
        Employee employee = new Employee("zhangsan", 19, 1000d);
        String json = new Gson().toJson(employee);
        log.info(json);

        /**
         * public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
         *         Object object = this.fromJson((String)json, (Type)classOfT);
         *         return Primitives.wrap(classOfT).cast(object);
         *     }
         */
        Map<String,Object> map1 = new Gson().fromJson(json,Map.class);
        map.entrySet().stream().forEach(System.out::println);

        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            System.out.println(stringObjectEntry.getKey());
        }


    }
}
