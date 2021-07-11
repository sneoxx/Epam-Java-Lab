package com.zaraev.epam.javacourses.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * DTO класс Customer
 */
@Data
public class CustomerDTO {

    private Integer customerId;

    @NotBlank(message = "Необходимо указать имя")
    private String customerName;

    @NotBlank(message = "Необходимо указать телефон")
    @Pattern(regexp = "\\+7[0-9]{10}", message = "Телефонный номер должен начинаться с +7, затем - 10 цифр")
    private String phone;

}