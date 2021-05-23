package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.service.ServiceEntity;

public class WorkDemonstrationOrderProduct {

    public void testSetOrderAndProduct() {
        ServiceEntity serviceEntity = new ServiceEntity();
        Product product = serviceEntity.getProduct(1);
        Order order = serviceEntity.getOrder(1);
        serviceEntity.createBondOrderAndProduct(order,product);
    }
}
