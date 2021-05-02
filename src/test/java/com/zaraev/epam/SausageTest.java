package com.zaraev.epam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SausageTest {

    Sausage sausage = new Sausage();

    @Test
    public void setType() {
        sausage.setType("Докторская");
        assertEquals(sausage.getType(), "Докторская");
    }

    @Test
    public void setCostTest() {
        sausage.setCost(100);
        assertEquals(sausage.getCost(), 100);
    }

    @Test
    public void setWeightTest() {
        sausage.setWeight(10);
        assertEquals(sausage.getWeight(), 10);
    }
}