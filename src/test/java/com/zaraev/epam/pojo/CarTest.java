package com.zaraev.epam.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {

    Car car = new Car();

    @Test
    public void setModelTest() {
        car.setModel("Kalina");
        assertEquals(car.getModel(), "Kalina");
    }

    @Test
    public void setBodyTypeTest() {
        car.setBodyType("pickup ");
        assertEquals(car.getBodyType(), "pickup ");
    }
}