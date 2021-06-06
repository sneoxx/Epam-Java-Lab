package com.zaraev.epam.javacourses.repository.impl;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс CRUD операций в БД для таблицы Order
 */
@Profile("!local")
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private final EntityManagerFactory entityManagerFactory;

    /**
     * Запись в БД екземпляра Order
     *
     * @param order - экземпляр customer для занесения в Order
     * @return - вернет занесенный экземпляр Order
     */
    @Override
    public Order create(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(order);
            transaction.commit();
            entityManager.close();
            log.info("createOrder() Объект Order занесен в БД: {}", order);
            return order;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра order
     *
     * @param order - экземпляр order, который необходимо изменить
     */
    @Override
    public Order update(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            log.debug("updateCustomer() Объект order передан на обновление: {} ", order);
            //  order = orderService.updateOrder(order);
            transaction.begin();
            entityManager.merge(order);
            transaction.commit();
            entityManager.close();
            log.info("updateOrder() Объект order успешно обновлен: {} ", order);
            return order;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Получение из БД объекта Order
     *
     * @param id - id объекта Order который необходимо получить
     * @return - объект Order из БД
     */
    @Override
    public Order get(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Order order = null;
        try {
            order = entityManager.find(Order.class, id);
            log.info("getOrder() Объект order успешно получен из БД {}", order);
            entityManager.close();
            return order;
        } catch (Exception e) {
            log.error("getOrder() Ошибка получения из БД объекта order: ", e);
        } finally {
            entityManager.close();
        }
        return order;
    }

    /**
     * Получение из БД всех объектов Order
     *
     * @return - Коллекция List всех объектов Order из БД
     */
    @Override
    public List<Order> getAllOrder() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Order> orderList = new ArrayList<>();
        try {
            String query = "SELECT o FROM Order o";
            orderList = entityManager
                    .createQuery(query, Order.class)
                    .getResultList();
            entityManager.close();
            log.info("getAllOrder() Выведен список всех Order: {}", orderList);
            return orderList;
        } catch (Exception e) {
            log.error("getAllOrder() Ошибка получения из БД объектов order: ", e);
        } finally {
            entityManager.close();
        }
        return orderList;
    }


    /**
     * Удаление объекта order из БД по id
     *
     * @param id - id удаляемого order
     */
    @Override
    public void delete(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Order tempOrder = entityManager.find(Order.class, id);
            transaction.begin();
            log.debug("deleteOrderWithId() Объект order передан на удаление: {}", tempOrder);
            if (entityManager.contains(tempOrder)) {
                entityManager.remove(tempOrder);
                transaction.commit();
                log.info("deleteOrderWithId() Объект order успешно удален: {}", tempOrder);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteOrderWithId() Ошибка удаления объекта order: ", e);
        } finally {
            entityManager.close();
        }
    }
}