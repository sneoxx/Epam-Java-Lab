package com.zaraev.epam.javacourses.repository.stub;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.repository.impl.IOrderProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
@Slf4j
public class OrderAndProductRepositoryStub implements IOrderProductRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private Environment environment;

    /**
     * Создание связи между order и product
     *
     * @param order   - объект order
     * @param product - объект product
     */
    public void createBondOrderAndProduct(Order order, Product product) {
        log.info("createBondOrderAndProduct() Объект order и product застаблен ");
        return;
    }
}