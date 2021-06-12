package com.zaraev.epam.javacourses.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * DTO класс Order
 */
@Data
public class OrderDTO {

    private Integer orderId;

    private String orderNumber;

    private Timestamp orderDate;

    private BigDecimal totalAmount;

    private Integer customerId;

    private Set<Integer> products;

}