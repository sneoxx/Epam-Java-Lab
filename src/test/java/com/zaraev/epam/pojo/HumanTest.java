package com.zaraev.epam.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumanTest {

    Human human = new Human();

    @Test
    public void setAgeTest() {
        human.setAge(4);
        assertEquals(human.getAge(), 4);
    }

    @Test
    public void setNameTest() {
        human.setName("Vasya");
        assertEquals(human.getName(), "Vasya");
    }
}