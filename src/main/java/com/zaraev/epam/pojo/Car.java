package com.zaraev.epam.pojo;

import com.zaraev.epam.annotation.Entity;
import com.zaraev.epam.annotation.Value;

/**
 * POJO Class Car
 */
@Entity
public class Car {

    @Value("Vaz")
    String model;

    String bodyType;

    public String getModel() {
        return model;
    }

    @Value("Sedan")
    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}