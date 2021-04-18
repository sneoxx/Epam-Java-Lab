package com.zaraev.epam.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BirdTest {
    Bird bird = new Bird();

    @Test
    public void setAgeTest() {
        bird.setAge(4);
        assertEquals(bird.getAge(), 4);
    }

    @Test
    public void setNameTest() {
        bird.setName("Lesya");
        assertEquals(bird.getName(), "Lesya");
    }
}