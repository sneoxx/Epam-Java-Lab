package com.zaraev.epam.javacourses.dto;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * DTO класс Customer
 */
@Data
public class CustomerDTO {

    @Expose
    private Integer customerId;

    @Expose
    private String customerName;

    @Expose
    private String phone;

}