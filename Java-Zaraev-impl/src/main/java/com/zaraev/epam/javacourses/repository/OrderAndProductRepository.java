package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.repository.impl.IOrderProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Profile("!local")
@Component
@Slf4j
public class OrderAndProductRepository implements IOrderProductRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * Создание связи между order и product
     *
     * @param order   - объект order
     * @param product - объект product
     */
    public void createBondOrderAndProduct(Order order, Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Order tempOrder = entityManager.find(Order.class, order.getOrderId());
            Product tempProduct = entityManager.find(Product.class, product.getProductId());
            transaction.begin();
            tempOrder.getProducts().add(tempProduct);
            tempProduct.getOrders().add(tempOrder);
            entityManager.persist(tempOrder);
            entityManager.persist(tempProduct);
            transaction.commit();
            entityManager.close();
            log.info("createBondOrderAndProduct() Создана связь многие ко многим у объектов {} и {}:", order, product);
        } catch (Exception e) {
            log.error("createBondOrderAndProduct() Ошибка создания связи многие ко многим: ", e);
        } finally {
            entityManager.close();
        }
    }
}