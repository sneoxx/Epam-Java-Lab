package com.zaraev.epam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sausage {

    private String type;

    private int weight;

    private long cost;
}