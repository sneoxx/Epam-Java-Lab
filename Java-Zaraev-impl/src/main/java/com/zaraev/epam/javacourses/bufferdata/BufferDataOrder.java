package com.zaraev.epam.javacourses.bufferdata;

import com.google.gson.annotations.Expose;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Промежуточный класс буффер для проверки корректности данных json в запросе
 */
@Data
public class BufferDataOrder {

    @Expose
    private Integer orderId;

    @Expose
    private String orderNumber;

    @Expose
    private Timestamp orderDate;

    @Expose
    private BigDecimal totalAmount;

    @Expose
    private Customer customer;

    @Expose
    private Set<Product> products;

}