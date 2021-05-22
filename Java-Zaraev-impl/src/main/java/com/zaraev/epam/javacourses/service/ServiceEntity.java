package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

@Slf4j
public class ServiceEntity {
    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WER");
    static int orderNumber;

    /**
     *
     */
    public void testCustomer() {
        Customer customer = createCustomer();
        Customer customer1 = createCustomer();
        getCustomer(1);
        updateCustomer(customer);
        deleteCustomer(customer1);
    }

    /**
     *
     */
    public void testOrder() {
        Customer customer = createCustomer();
        Customer customer1 = createCustomer();
        Order order = createOrder(customer);
        Order order1 = createOrder(customer1);
        getOrder(1);
        updateOrder(order);
        deleteOrder(order1);
    }

    /**
     *
     */
    public void testSupplier() {
        Supplier supplier = createSupplier();
        Supplier supplier1 = createSupplier();
        getSupplier(1);
        updateSupplier(supplier);
        deleteSupplier(supplier1);
    }

    /**
     *
     */
    public void testProduct() {
        Supplier supplier = createSupplier();
        Supplier supplier1 = createSupplier();
        Product product = createProduct(supplier);
        Product product1 = createProduct(supplier1);
        getProduct(1);
        updateProduct(product);
        deleteProduct(product1);
    }

    /**
     *
     * @return
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
     *
     * @param customer
     * @return
     */
    public Order createOrder(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerID());
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
     *
     * @return
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
     *
     * @param supplier
     * @return
     */
    public Product createProduct(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Supplier tempSupplier = entityManager.find(Supplier.class, supplier.getSupplierId());
        Product product = new Product();
        product.setProductName(generateRandomWord());
        product.setDiscount(true);
        product.setUnitprice(BigDecimal.valueOf(100 + orderNumber));
        product.setSupplier(tempSupplier);
        transaction.begin();
        entityManager.persist(product);
        transaction.commit();
        entityManager.close();
        log.info("createProduct() Объект Product создан и занесен в БД: {}", product);
        return product;
    }

    /**
     *
     * @param customer
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
     *
     * @param order
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
     *
     * @param product
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
     *
     * @param id
     * @return
     */
    public Customer getCustomer(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = entityManager.find(Customer.class, id);
        log.info("getCustomer() Объект customer успешно получен из БД {}", customer);
        entityManager.close();
        return customer;
    }

    /**
     *
     * @param id
     * @return
     */
    public Order getOrder(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Order order = entityManager.find(Order.class, id);
        log.info("getOrder() Объект customer успешно получен из БД {}", order);
        entityManager.close();
        return order;
    }

    /**
     *
     * @param id
     * @return
     */
    public Supplier getSupplier(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Supplier supplier = entityManager.find(Supplier.class, id);
        log.info("getSupplier() Объект supplier успешно получен из БД {}", supplier);
        entityManager.close();
        return supplier;
    }

    /**
     *
     * @param id
     * @return
     */
    public Product getProduct(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = entityManager.find(Product.class, id);
        log.info("getProduct() Объект product успешно получен из БД {}", product);
        entityManager.close();
        return product;
    }

    /**
     *
     * @param customer
     */
    public void deleteCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerID());
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
     *
     * @param order
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
     *
     * @param supplier
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
     *
     * @param product
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
