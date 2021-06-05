package com.zaraev.epam.javacourses.demonstration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkDemonstrationAll {

    @Autowired
    private WorkDemonstrationCustomer workDemonstrationCustomer ;

    @Autowired
    private WorkDemonstrationProduct workDemonstrationProduct;

    @Autowired
    private WorkDemonstrationSupplier workDemonstrationSupplier;

    @Autowired
    private WorkDemonstrationOrder workDemonstrationOrder;

    @Autowired
    private WorkDemonstrationOrderProduct workDemonstrationOrderProduct;

    /**
     * Метод для демонстрации работы операций CRUD всех сущностей БД
     */
    public void test() {
        workDemonstrationCustomer.test();
        workDemonstrationSupplier.test();
        workDemonstrationProduct.test();
        workDemonstrationProduct.test();
    }
}