package com.zaraev.epam.javacourses.bufferdata;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * Промежуточный класс буффер для проверки корректности данных json в запросе
 */
@Data
public class BufferDataCustomer {

    @Expose
    private Integer customerId;

    @Expose
    private String customerName;

    @Expose
    private String phone;

}