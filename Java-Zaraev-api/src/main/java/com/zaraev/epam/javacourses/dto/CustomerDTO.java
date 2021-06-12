package com.zaraev.epam.javacourses.dto;

import lombok.Data;

/**
 * DTO класс Customer
 */
@Data
public class CustomerDTO {

    private Integer customerId;

    private String customerName;

    private String phone;

}