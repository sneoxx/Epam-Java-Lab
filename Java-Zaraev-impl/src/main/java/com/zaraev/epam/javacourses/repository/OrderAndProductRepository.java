package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository
@Slf4j
public class OrderAndProductRepository {


//    @PersistenceContext( type = PersistenceContextType.TRANSACTION)
//    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;// = Persistence.createEntityManagerFactory("WER");

    /**
     * Создание связи между order и product
     * @param order - объект order
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



//    /**
//     * Создание связи между order и product
//     * @param order - объект order
//     * @param product - объект product
//     */
//    @Transactional
//    public void createBondOrderAndProduct(Order order, Product product) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            Order tempOrder = entityManager.find(Order.class, order.getOrderId());
//            Product tempProduct = entityManager.find(Product.class, product.getProductId());
////            transaction.begin();
//            tempOrder.getProducts().add(tempProduct);
//            tempProduct.getOrders().add(tempOrder);
//            entityManager.persist(tempOrder);
//            entityManager.persist(tempProduct);
////            transaction.commit();
//            entityManager.close();
//            log.info("createBondOrderAndProduct() Создана связь многие ко многим у объектов {} и {}:", order, product);
//        } catch (Exception e) {
//            log.error("createBondOrderAndProduct() Ошибка создания связи многие ко многим: ", e);
//        } finally {
//            entityManager.close();
//        }
//    }
}