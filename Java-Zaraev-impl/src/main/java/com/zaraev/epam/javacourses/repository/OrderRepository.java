package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Order;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderRepository {
    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WER");
    //  public OrderService orderService = new OrderService();

//    /**
//         * Создание и занесение в БД екземпляра Order
//         *
//         * @param customer - экземпляр customer для занесения в Order
//         * @return - вернет занесенный экземпляр Order
//         */
//        public Order createOrder(Customer customer) {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
//            EntityTransaction transaction = entityManager.getTransaction();
//            try {
//                Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerId());
//                Order order = new Order();
//                order.setOrderNumber(getRandomNumber());
//                order.setOrderDate(new Timestamp(System.currentTimeMillis()));
//                order.setTotalAmount(BigDecimal.valueOf(100));
//                order.setCustomer(tempCustomer);
//                transaction.begin();
//                entityManager.persist(order);
//                transaction.commit();
//                entityManager.close();
//                log.info("createOrder() Объект Order создан и занесен в БД: {}", customer);
//                return order;
//            } finally {
//                entityManager.close();
//            }
//        }

    /**
     * Запись в БД екземпляра Order
     *
     * @param order - экземпляр customer для занесения в Order
     * @return - вернет занесенный экземпляр Order
     */
    public Order create(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // Order order = orderService.createRandomOrder(customer);
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
    public void update(Order order) {
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
        } catch (Exception e) {
            log.error("updateOrder() Ошибка обновления объекта order: ", e);
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
    public Order getOrder(int id) {
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
     * Удаление объекта order из БД
     *
     * @param order - удаляемый объект
     */
    public void deleteOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Order tempOrder = entityManager.find(Order.class, order.getOrderId());
            transaction.begin();
            log.debug("deleteOrder() Объект order передан на удаление: {}", tempOrder);
            if (entityManager.contains(tempOrder)) {
                entityManager.remove(tempOrder);
                transaction.commit();
                log.info("deleteOrder() Объект order успешно удален: {}", tempOrder);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteOrder() Ошибка удаления объекта order: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Удаление объекта order из БД по id
     *
     * @param id - id удаляемого order
     */
    public void deleteOrderWithId(int id) {
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