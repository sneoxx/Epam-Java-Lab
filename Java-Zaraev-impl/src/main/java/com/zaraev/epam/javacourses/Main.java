package com.zaraev.epam.javacourses;

import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationCustomer;
import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationOrder;
import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationProduct;
import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationSupplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        WorkDemonstrationCustomer workDemonstrationCustomer = new WorkDemonstrationCustomer();
        WorkDemonstrationOrder workDemonstrationOrder = new WorkDemonstrationOrder();
        WorkDemonstrationProduct workDemonstrationProduct = new WorkDemonstrationProduct();
        WorkDemonstrationSupplier workDemonstrationSupplier = new WorkDemonstrationSupplier();
        workDemonstrationCustomer.testCustomer();
        workDemonstrationOrder.testOrder();
        workDemonstrationProduct.testProduct();
        workDemonstrationSupplier.testSupplier();
    }
}