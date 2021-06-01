package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.repository.OrderAndProductRepository;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.repository.ProductRepository;

public class WorkDemonstrationOrderProduct {
    OrderRepository orderRepository= new OrderRepository();
    ProductRepository productRepository = new ProductRepository();
    OrderAndProductRepository orderAndProductRepository = new OrderAndProductRepository();

    /**
     * Метод для демонстрации работы OrderAndProduct
     */
    public void testSetOrderAndProduct() {
        Product product = productRepository.getProduct(1);
        Order order = orderRepository.getOrder(1);
        orderAndProductRepository.createBondOrderAndProduct(order,product);
    }
}