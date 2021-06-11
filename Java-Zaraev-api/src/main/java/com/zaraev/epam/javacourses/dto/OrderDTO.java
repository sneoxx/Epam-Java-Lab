package com.zaraev.epam.javacourses.dto;


import com.google.gson.annotations.Expose;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * DTO класс Order
 */
@Data
public class OrderDTO {

    @Expose
    private Integer orderId;

    @Expose
    private String orderNumber;

    @Expose
    private Timestamp orderDate;

    @Expose
    private BigDecimal totalAmount;

    @Expose
    private Integer customerId;

    @Expose
    private Set<Integer> products;

}