package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.service.ServiceEntity;

public class WorkDemonstrationOrder {

    ServiceEntity serviceEntity = new ServiceEntity();

    /**
     * Метод для демонстрации работы операций CRUD класса Order
     */
    public void testOrder() {
        Customer customer = serviceEntity.createCustomer();
        Customer customer1 = serviceEntity.createCustomer();
        Order order = serviceEntity.createOrder(customer);
        Order order1 = serviceEntity.createOrder(customer1);
        serviceEntity.getOrder(1);
        serviceEntity.updateOrder(order);
        serviceEntity.deleteOrder(order1);

    }
}