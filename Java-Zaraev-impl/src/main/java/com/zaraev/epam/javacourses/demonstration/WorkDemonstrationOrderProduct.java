package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.repository.impl.IOrderProductRepository;
import com.zaraev.epam.javacourses.repository.impl.IOrderRepository;
import com.zaraev.epam.javacourses.repository.impl.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkDemonstrationOrderProduct {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IOrderProductRepository orderAndProductRepository;

    @Autowired
    private Environment environment;

    /**
     * Метод для демонстрации работы OrderAndProduct
     */
    public void test() {
        for (String profileName : environment.getActiveProfiles()) {
            log.info("Активный профиль: " + profileName);
        }
        Product product = productRepository.getProduct(1);
        Order order = orderRepository.getOrder(1);
        orderAndProductRepository.createBondOrderAndProduct(order,product);
    }
}