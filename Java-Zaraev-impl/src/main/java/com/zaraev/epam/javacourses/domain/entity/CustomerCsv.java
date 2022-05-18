package com.zaraev.epam.javacourses.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomerCsv {

    private Integer customerId;

    private String customerName;

    private String phone;

    private List<Order> orders = new ArrayList<>();    // клиент один а заказов много

}
