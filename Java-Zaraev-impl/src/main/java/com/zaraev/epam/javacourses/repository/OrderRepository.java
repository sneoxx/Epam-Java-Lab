package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.service.EService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class OrderRepository implements EService {

//    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
//    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;// = Persistence.createEntityManagerFactory("WER");


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
     * Получение из БД объекта Order по полю orderNumber Order
     *
     * @param orderNumber- id объекта Order который необходимо получить
     * @return - объект Order из БД
     */
    public Order getOrderWithInstance(String orderNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Order order = null;
        try {
            TypedQuery<Order> query = entityManager.createQuery(
                    "SELECT u FROM Order u WHERE u.orderNumber = :orderNumber", Order.class);
            order = query.setParameter("orderNumber", orderNumber)
                    .getSingleResult();
            log.info("getCustomer() Объект order успешно получен из БД {}", order);
            entityManager.close();
            return order;
        } catch (Exception e) {
            log.error("getCustomer() Ошибка получения из БД объекта order: ", e);
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




//
//
//    /**
//     * Запись в БД екземпляра Order
//     *
//     * @param order - экземпляр customer для занесения в Order
//     * @return - вернет занесенный экземпляр Order
//     */
//    @Transactional
//    public Order create(Order order) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        EntityTransaction transaction = entityManager.getTransaction();
//        try {
////            transaction.begin();
//            entityManager.persist(order);
////            transaction.commit();
//            entityManager.close();
//            log.info("createOrder() Объект Order занесен в БД: {}", order);
//            return order;
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    /**
//     * Изменение в БД экземпляра order
//     *
//     * @param order - экземпляр order, который необходимо изменить
//     */
//    @Transactional
//    public void update(Order order) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            log.debug("updateCustomer() Объект order передан на обновление: {} ", order);
//            //  order = orderService.updateOrder(order);
////            transaction.begin();
//            entityManager.merge(order);
////            transaction.commit();
//            entityManager.close();
//            log.info("updateOrder() Объект order успешно обновлен: {} ", order);
//        } catch (Exception e) {
//            log.error("updateOrder() Ошибка обновления объекта order: ", e);
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    /**
//     * Получение из БД объекта Order
//     *
//     * @param id - id объекта Order который необходимо получить
//     * @return - объект Order из БД
//     */
//    public Order getOrder(int id) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Order order = null;
//        try {
//            order = entityManager.find(Order.class, id);
//            log.info("getOrder() Объект order успешно получен из БД {}", order);
//            entityManager.close();
//            return order;
//        } catch (Exception e) {
//            log.error("getOrder() Ошибка получения из БД объекта order: ", e);
//        } finally {
//            entityManager.close();
//        }
//        return order;
//    }
//
//    /**
//     * Получение из БД объекта Order по полю orderNumber Order
//     *
//     * @param orderNumber- id объекта Order который необходимо получить
//     * @return - объект Order из БД
//     */
//    public Order getOrderWithInstance(String orderNumber) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Order order = null;
//        try {
////            TypedQuery<Order> query = entityManager.createQuery(
////                    "SELECT u FROM Order u WHERE u.orderNumber = :orderNumber", Order.class);
////            order = query.setParameter("orderNumber", orderNumber)
////                    .getSingleResult();
//
//            String query = "SELECT u FROM Order u WHERE u.orderNumber = :orderNumber";
//            order = entityManager
//                    .createQuery(query, Order.class)
//                    .getSingleResult();
//            log.info("getCustomer() Объект order успешно получен из БД {}", order);
//            entityManager.close();
//            return order;
//        } catch (Exception e) {
//            log.error("getCustomer() Ошибка получения из БД объекта order: ", e);
//        } finally {
//            entityManager.close();
//        }
//        return order;
//    }
//
//
//    /**
//     * Получение из БД всех объектов Order
//     *
//     * @return - Коллекция List всех объектов Order из БД
//     */
//    public List<Order> getAllOrder() {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<Order> orderList = new ArrayList<>();
//        try {
//            String query = "SELECT o FROM Order o";
//            orderList = entityManager
//                    .createQuery(query, Order.class)
//                    .getResultList();
//            entityManager.close();
//            log.info("getAllOrder() Выведен список всех Order: {}", orderList);
//            return orderList;
//        } catch (Exception e) {
//            log.error("getAllOrder() Ошибка получения из БД объектов order: ", e);
//        } finally {
//            entityManager.close();
//        }
//        return orderList;
//    }
//
//    /**
//     * Удаление объекта order из БД
//     *
//     * @param order - удаляемый объект
//     */
//    @Transactional
//    public void deleteOrder(Order order) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            Order tempOrder = entityManager.find(Order.class, order.getOrderId());
////            transaction.begin();
//            log.debug("deleteOrder() Объект order передан на удаление: {}", tempOrder);
//            if (entityManager.contains(tempOrder)) {
//                entityManager.remove(tempOrder);
////                transaction.commit();
//                log.info("deleteOrder() Объект order успешно удален: {}", tempOrder);
//            }
//            entityManager.close();
//        } catch (Exception e) {
//            log.error("deleteOrder() Ошибка удаления объекта order: ", e);
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    /**
//     * Удаление объекта order из БД по id
//     *
//     * @param id - id удаляемого order
//     */
//    @Transactional
//    public void deleteOrderWithId(int id) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            Order tempOrder = entityManager.find(Order.class, id);
////            transaction.begin();
//            log.debug("deleteOrderWithId() Объект order передан на удаление: {}", tempOrder);
//            if (entityManager.contains(tempOrder)) {
//                entityManager.remove(tempOrder);
////                transaction.commit();
//                log.info("deleteOrderWithId() Объект order успешно удален: {}", tempOrder);
//            }
//            entityManager.close();
//        } catch (Exception e) {
//            log.error("deleteOrderWithId() Ошибка удаления объекта order: ", e);
//        } finally {
//            entityManager.close();
//        }
//    }
//
//
//

}