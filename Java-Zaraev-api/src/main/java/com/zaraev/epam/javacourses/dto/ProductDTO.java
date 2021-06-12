package com.zaraev.epam.javacourses.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO класс Product
 */
@Data
public class ProductDTO {

    private Integer productId;

    private String productName;

    private BigDecimal unitPrice;

    private boolean isDiscountinued;

    private Integer supplierId;

}