package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.repositiry.ServiceEntity;

public class WorkDemonstrationCustomer {

    ServiceEntity serviceEntity = new ServiceEntity();
    /**
     * Метод для демонстрации работы операций CRUD класса Customer
     */
    public void testCustomer() {
        Customer customer = serviceEntity.createCustomer();
        Customer customer1 = serviceEntity.createCustomer();
        serviceEntity.getCustomer(1);
        serviceEntity.updateCustomer(customer);
        serviceEntity.deleteCustomer(customer1);
    }
}