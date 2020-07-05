package com.aspirecn.jdk8.method_ref;

import lombok.*;
import sun.reflect.CallerSensitive;

/**
 * @Title: Employee
 * @Package: com.aspirecn.jdk8.method_ref
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/6/29 - 16:03
 */
@Data
public class Employee {
    private String name;
    @NonNull Integer age;
    @NonNull Double salary;

    public Employee() {
    }

    public Employee(String name, @NonNull Integer age, @NonNull Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(@NonNull Integer age, @NonNull Double salary) {
        this.age = age;
        this.salary = salary;
    }

    public Employee(@NonNull Integer age) {
        this.age = age;
    }

}
