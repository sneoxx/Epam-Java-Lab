package com.zaraev.epam.javacourses.bufferdata;

import com.google.gson.annotations.Expose;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Промежуточный класс буффер для проверки корректности данных json в запросе
 */
@Data
public class BufferDataProduct {

    @Expose
    private Integer productId;

    @Expose
    private String productName;

    @Expose
    private BigDecimal unitPrice;

    @Expose
    private boolean isDiscountinued;

    @Expose
    private Supplier supplier;

}