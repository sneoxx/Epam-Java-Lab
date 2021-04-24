package com.zaraev.epam.pojo;

import com.zaraev.epam.annotation.Entity;
import com.zaraev.epam.annotation.Value;

/**
 * POJO Class Human
 */
@Entity
public class Human {

    @Value(valueInt = 1)
    private Integer age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Value("Petr")
    public void setName(String name) {
        this.name = name;
    }
}