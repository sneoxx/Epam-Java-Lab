package com.zaraev.epam.pojo;

import com.zaraev.epam.annotation.Entity;

/**
 * POJO Class Bird
 */
@Entity
public class Bird {


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

    public void setName(String name) {
        this.name = name;
    }
}