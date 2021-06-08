package com.zaraev.epam.javacourses.demonstration;


import org.springframework.context.ApplicationContext;

/**
 * Метод для демонстрации работы операций CRUD всех классов
 */
public class WorkDemonstrationAll {

    private WorkDemonstrationCustomer workDemonstrationCustomer;

    private WorkDemonstrationProduct workDemonstrationProduct;

    private WorkDemonstrationSupplier workDemonstrationSupplier;

    private WorkDemonstrationOrder workDemonstrationOrder;

    public WorkDemonstrationAll() {
        this.workDemonstrationCustomer = new WorkDemonstrationCustomer();
        this.workDemonstrationSupplier = new WorkDemonstrationSupplier();
        this.workDemonstrationProduct = new WorkDemonstrationProduct();
        this.workDemonstrationOrder = new WorkDemonstrationOrder();
    }

    /**
     * Метод для демонстрации работы операций CRUD всех сущностей БД
     */
    public void test(ApplicationContext context) {
        workDemonstrationCustomer.test(context);
        workDemonstrationSupplier.test(context);
        workDemonstrationProduct.test(context);
        workDemonstrationOrder.test(context);
    }
}