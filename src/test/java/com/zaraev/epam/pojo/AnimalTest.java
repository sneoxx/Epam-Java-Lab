package com.zaraev.epam.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    Animal animal = new Animal();

    @Test
    public void setAgeTest() {
        animal.setAge(4);
        assertEquals(animal.getAge(), 4);
    }

    @Test
    public void setNameTest() {
        animal.setName("Lesya");
        assertEquals(animal.getName(), "Lesya");
    }

}