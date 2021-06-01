package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.repositiry.ServiceEntity;

public class WorkDemonstrationOrderProduct {
    ServiceEntity serviceEntity = new ServiceEntity();

    /**
     * Метод для демонстрации работы OrderAndProduct
     */
    public void testSetOrderAndProduct() {
        Product product = serviceEntity.getProduct(1);
        Order order = serviceEntity.getOrder(1);
        serviceEntity.createBondOrderAndProduct(order,product);
    }
}