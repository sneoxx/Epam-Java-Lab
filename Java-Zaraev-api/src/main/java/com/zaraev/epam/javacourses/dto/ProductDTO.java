package com.zaraev.epam.javacourses.dto;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO класс Product
 */
@Data
public class ProductDTO {

    @Expose
    private Integer productId;

    @Expose
    private String productName;

    @Expose
    private BigDecimal unitPrice;

    @Expose
    private boolean isDiscountinued;

    @Expose
    private Integer supplierId;

}