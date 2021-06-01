package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.repository.OrderAndProductRepository;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.service.EService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WorkDemonstrationOrderProduct implements EService {

    @Autowired
    private OrderRepository orderRepository;// = new OrderRepository();

    @Autowired
    private ProductRepository productRepository;// = new ProductRepository();

    @Autowired
    private OrderAndProductRepository orderAndProductRepository;// = new OrderAndProductRepository();

    /**
     * Метод для демонстрации работы OrderAndProduct
     */
    public void testSetOrderAndProduct() {
        Product product = productRepository.getProduct(1);
        Order order = orderRepository.getOrder(1);
        orderAndProductRepository.createBondOrderAndProduct(order,product);
    }
}