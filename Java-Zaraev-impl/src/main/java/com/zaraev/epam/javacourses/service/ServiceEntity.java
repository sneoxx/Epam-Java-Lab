package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

@Slf4j
public class ServiceEntity {
    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WER");
    static int orderNumber;


    /**
     * Создание и занесение в БД екземпляра Customer
     *
     * @return вернет занесенный экземпляр Customer
     */
    public Customer createCustomer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Customer customer = new Customer();
        customer.setCustomerName(generateRandomWord());
        customer.setPhone(getRandomNumber());
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();
        log.info("createCustomer() Объект Customer создан и занесен в БД: {}", customer);
        return customer;
    }

    /**
     * Создание и занесение в БД екземпляра Order
     *
     * @param customer - экземпляр customer для занесения в Order
     * @return - вернет занесенный экземпляр Order
     */
    public Order createOrder(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerId());
        Order order = new Order();
        order.setOrderNumber(getRandomNumber());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(BigDecimal.valueOf(100 + orderNumber));
        order.setCustomer(tempCustomer);
        transaction.begin();
        entityManager.persist(order);
        transaction.commit();
        entityManager.close();
        log.info("createOrder() Объект Order создан и занесен в БД: {}", customer);
        return order;
    }

    /**
     * Создание и занесение в БД екземпляра Supplier
     *
     * @return вернет занесенный экземпляр Supplier
     */
    public Supplier createSupplier() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Supplier supplier = new Supplier();
        supplier.setCompanyName(generateRandomWord());
        supplier.setPhone(getRandomNumber());
        transaction.begin();
        entityManager.persist(supplier);
        transaction.commit();
        entityManager.close();
        log.info("createSupplier() Объект Supplier создан и занесен в БД: {}", supplier);
        return supplier;
    }

    /**
     * Создание и занесение в БД екземпляра Product
     *
     * @param supplier - экземпляр supplier для занесения в Product
     * @return - вернет занесенный экземпляр Product
     */
    public Product createProduct(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Supplier tempSupplier = entityManager.find(Supplier.class, supplier.getSupplierId());
        Product product = new Product();
        product.setProductName(generateRandomWord());
        product.setDiscountinued(true);
        product.setUnitPrice(BigDecimal.valueOf(100 + orderNumber));
        product.setSupplier(tempSupplier);
        transaction.begin();
        entityManager.persist(product);
        transaction.commit();
        entityManager.close();
        log.info("createProduct() Объект Product создан и занесен в БД: {}", product);
        return product;
    }

    /**
     * Создание связи между Order Product
     * @param order - соединяемый order
     * @param product - соединяемый product
     */
    public void createBondOrderAndProduct(Order order, Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
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
    }

    /**
     * Изменение в БД экземпляра customer
     *
     * @param customer - экземпляр customer, который необходимо изменить
     */
    public void updateCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        log.debug("updateCustomer() Объект сustomer передан на обновление: {} ", customer);
        customer.setCustomerName(customer.getCustomerName() + "+" + generateRandomWord());
        transaction.begin();
        entityManager.merge(customer);
        transaction.commit();
        entityManager.close();
        log.info("updateCustomer() Объект сustomer успешно обновлен: {} ", customer);
    }

    /**
     * Изменение в БД экземпляра order
     *
     * @param order - экземпляр order, который необходимо изменить
     */
    public void updateOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        log.debug("updateCustomer() Объект order передан на обновление: {} ", order);
        order.setOrderNumber(order.getOrderNumber() + "+" + generateRandomWord());
        transaction.begin();
        entityManager.merge(order);
        transaction.commit();
        entityManager.close();
        log.info("updateOrder() Объект order успешно обновлен: {} ", order);
    }

    /**
     * Изменение в БД экземпляра supplier
     *
     * @param supplier - экземпляр supplier, который необходимо изменить
     */
    public void updateSupplier(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        log.debug("updateSupplier() Объект supplier передан на обновление: {} ", supplier);
        supplier.setCompanyName(supplier.getCompanyName() + "+" + generateRandomWord());
        transaction.begin();
        entityManager.merge(supplier);
        transaction.commit();
        log.info("updateSupplier() Объект supplier успешно обновлен: {} ", supplier);
        entityManager.close();
    }

    /**
     * Изменение в БД экземпляра product
     *
     * @param product - экземпляр product, который необходимо изменить
     */
    public void updateProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        log.debug("updateProduct() Объект product передан на обновление: {} ", product);
        product.setProductName(product.getProductName() + "+" + generateRandomWord());
        transaction.begin();
        entityManager.merge(product);
        transaction.commit();
        entityManager.close();
        log.info("updateProduct() Объект product успешно обновлен: {} ", product);
    }

    /**
     * Получение из БД объекта Customer
     *
     * @param id - id объекта Customer который необходимо получить
     * @return - объект Customer из БД
     */
    public Customer getCustomer(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = entityManager.find(Customer.class, id);
        log.info("getCustomer() Объект customer успешно получен из БД {}", customer);
        entityManager.close();
        return customer;
    }

    /**
     * Получение из БД объекта Order
     *
     * @param id - id объекта Order который необходимо получить
     * @return - объект Order из БД
     */
    public Order getOrder(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Order order = entityManager.find(Order.class, id);
        log.info("getOrder() Объект customer успешно получен из БД {}", order);
        entityManager.close();
        return order;
    }

    /**
     * Получение из БД объекта Supplier
     *
     * @param id - id объекта Supplier который необходимо получить
     * @return - объект Supplier из БД
     */
    public Supplier getSupplier(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Supplier supplier = entityManager.find(Supplier.class, id);
        log.info("getSupplier() Объект supplier успешно получен из БД {}", supplier);
        entityManager.close();
        return supplier;
    }

    /**
     * Получение из БД объекта Product
     *
     * @param id - id объекта Product который необходимо получить
     * @return - объект Product из БД
     */
    public Product getProduct(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = entityManager.find(Product.class, id);
        log.info("getProduct() Объект product успешно получен из БД {}", product);
        entityManager.close();
        return product;
    }

    /**
     * Удаление объекта customer из БД
     *
     * @param customer - удаляемый объект
     */
    public void deleteCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerId());
        transaction.begin();
        log.debug("deleteCustomer() Объект customer передан на удаление: {}", tempCustomer);
        if (entityManager.contains(tempCustomer)) {
            entityManager.remove(tempCustomer);
            transaction.commit();
            log.info("deleteCustomer() Объект customer успешно удален: {}", tempCustomer);
        }
        entityManager.close();
    }

    /**
     * Удаление объекта order из БД
     *
     * @param order - удаляемый объект
     */
    public void deleteOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Order tempOrder = entityManager.find(Order.class, order.getOrderId());
        transaction.begin();
        log.debug("deleteOrder() Объект order передан на удаление: {}", tempOrder);
        if (entityManager.contains(tempOrder)) {
            entityManager.remove(tempOrder);
            transaction.commit();
            log.info("deleteOrder() Объект order успешно удален: {}", tempOrder);
        }
        entityManager.close();
    }

    /**
     * Удаление объекта supplier из БД
     *
     * @param supplier - удаляемый объект
     */
    public void deleteSupplier(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Supplier tempSupplier = entityManager.find(Supplier.class, supplier.getSupplierId());
        transaction.begin();
        log.debug("deleteSupplier() Объект supplier передан на удаление: {}", tempSupplier);
        if (entityManager.contains(tempSupplier)) {
            entityManager.remove(tempSupplier);
            transaction.commit();
            log.info("deleteSupplier() Объект supplier успешно удален: {}", tempSupplier);
        }
        entityManager.close();
    }

    /**
     * Удаление объекта product из БД
     *
     * @param product - удаляемый объект
     */
    public void deleteProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Product tempProduct = entityManager.find(Product.class, product.getProductId());
        transaction.begin();
        log.debug("deleteProduct() Объект supplier передан на удаление: {}", tempProduct);
        if (entityManager.contains(tempProduct)) {
            entityManager.remove(tempProduct);
            transaction.commit();
            log.info("deleteProduct() Объект supplier успешно удален: {}", tempProduct);
        }
        entityManager.close();
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