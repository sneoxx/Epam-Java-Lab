package com.zaraev.epam.javacourses;

import com.zaraev.epam.javacourses.service.ServiceEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ServiceEntity serviceEntityCustomer = new ServiceEntity();
        serviceEntityCustomer.testCustomer();
        serviceEntityCustomer.testOrder();
        serviceEntityCustomer.testSupplier();
        serviceEntityCustomer.testProduct();
    }
}