package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.bufferdata.BufferDataOrder;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
public class OrderRepository {
    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WER");


        /**
         * Создание и занесение в БД екземпляра Order
         *
         * @param customer - экземпляр customer для занесения в Order
         * @return - вернет занесенный экземпляр Order
         */
        public Order createOrder(Customer customer) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerId());
                Order order = new Order();
                order.setOrderNumber(getRandomNumber());
                order.setOrderDate(new Timestamp(System.currentTimeMillis()));
                order.setTotalAmount(BigDecimal.valueOf(100));
                order.setCustomer(tempCustomer);
                transaction.begin();
                entityManager.persist(order);
                transaction.commit();
                entityManager.close();
                log.info("createOrder() Объект Order создан и занесен в БД: {}", customer);
                return order;
            } finally {
                entityManager.close();
            }
        }

        /**
         * Создание и занесение в БД екземпляра Order на основании объекта BufferDataOrder c проверкой наличия в базе
         * @param bufferDataOrder - Экземпляр BufferDataOrder
         * @return - экземпляр order
         */
        public Order createOrderWithInstanceBuf(BufferDataOrder bufferDataOrder) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                Order order = new Order();
                order.setOrderId(null);
                order.setOrderNumber(bufferDataOrder.getOrderNumber());
                order.setOrderDate(bufferDataOrder.getOrderDate());
                order.setTotalAmount(bufferDataOrder.getTotalAmount());
                Customer tempCustomer = bufferDataOrder.getCustomer();
                order.setCustomer(entityManager.find(Customer.class, tempCustomer.getCustomerId()));
                Set<Product> products = new HashSet<>();
                Set<Product> tempProducts = bufferDataOrder.getProducts();
                for (Product product : tempProducts) {
                    Product foundProduct = entityManager.find(Product.class, product.getProductId());
                    products.add(foundProduct);
                }
                order.setProducts(products);
                transaction.begin();
                entityManager.persist(order);
                transaction.commit();
                entityManager.close();
                log.info("createOrderWithInstance() Объект Order создан и занесен в БД: {}", order);
                return order;
            } finally {
                entityManager.close();
            }
        }

        /**
         * Создание связи между Order Product
         *
         * @param order   - соединяемый order
         * @param product - соединяемый product
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

        /**
         * Изменение в БД экземпляра order
         *
         * @param order - экземпляр order, который необходимо изменить
         */
        public void updateOrder(Order order) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                log.debug("updateCustomer() Объект order передан на обновление: {} ", order);
                order.setOrderNumber(order.getOrderNumber() + "+" + generateRandomWord());
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
         * Изменение в БД экземпляра order
         *
         * @param id    - id экземпляра order в базе, который необходимо изменить
         * @param order - экземпляр order, который необходимо изменить
         */
        public void updateOrderWithId(int id, Order order) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                Order updateOrder = entityManager.find(Order.class, id);
                log.debug("updateProductWithId() Объект order передан на обновление: {} ", order);
                updateOrder.setOrderNumber(order.getOrderNumber());
                updateOrder.setOrderDate(order.getOrderDate());
                updateOrder.setTotalAmount(order.getTotalAmount());
                transaction.begin();
                entityManager.merge(updateOrder);
                transaction.commit();
                log.info("updateProductWithId() Объект order успешно обновлен: {} ", order);
                entityManager.close();
            } catch (Exception e) {
                log.error("updateProductWithId() Ошибка обновления объекта order: ", e);
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

        /**
         * Генерация случайного слова
         *
         * @return - случайное слово
         */
        public static String generateRandomWord() {
            Random random = new Random();
            char[] word = new char[random.nextInt(2) + 3];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            return new String(word);
        }

        /**
         * Генерация случайного числа в заданном диапазоне
         *
         * @return - случайное число
         */
        public String getRandomNumber() {
            return Integer.toString(1 + (int) (Math.random() * 10000));
        }

}
