package com.zaraev.epam.javacourses;

import com.zaraev.epam.javacourses.demonstration.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        WorkDemonstrationCustomer workDemonstrationCustomer = new WorkDemonstrationCustomer();
        WorkDemonstrationOrder workDemonstrationOrder = new WorkDemonstrationOrder();
        WorkDemonstrationProduct workDemonstrationProduct = new WorkDemonstrationProduct();
        WorkDemonstrationSupplier workDemonstrationSupplier = new WorkDemonstrationSupplier();
        WorkDemonstrationOrderProduct workDemonstrationOrderProduct = new WorkDemonstrationOrderProduct();
        workDemonstrationCustomer.testCustomer();
        workDemonstrationOrder.testOrder();
        workDemonstrationProduct.testProduct();
        workDemonstrationSupplier.testSupplier();
        workDemonstrationOrderProduct.testSetOrderAndProduct();
    }
}