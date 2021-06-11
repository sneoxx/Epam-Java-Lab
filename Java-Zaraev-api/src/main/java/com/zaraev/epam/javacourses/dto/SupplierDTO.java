package com.zaraev.epam.javacourses.dto;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * DTO класс Supplier
 */
@Data
public class SupplierDTO {

    @Expose
    private Integer supplierId;

    @Expose
    private String companyName;

    @Expose
    private String phone;

}