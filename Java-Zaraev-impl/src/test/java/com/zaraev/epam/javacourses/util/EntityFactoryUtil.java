package com.zaraev.epam.javacourses.util;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

public final class EntityFactoryUtil {

    /**
     * Создание и запись в БД рандомного Customer
     *
     * @return - сustomer записанный в базу
     */

    public static Customer createRandomCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(generateRandomWord());
        customer.setPhone(getRandomNumber());
        return customer;
    }

    /**
     * Создание и запись в БД рандомного Order
     *
     * @param customer - экземпляр customer для записи
     * @return - order записанный в базу
     */
    public static Order createRandomOrder(Customer customer) {
        Order order = new Order();
        order.setOrderNumber(getRandomNumber());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(BigDecimal.valueOf(100));
        order.setCustomer(customer);
        return order;
    }

    /**
     * Создание случайного product и запись в БД
     *
     * @param supplier - экземпляр supplier для записи
     * @return - product записанный в базу
     */
    public static Product createRandomProduct(Supplier supplier) {
        Product product = new Product();
        product.setProductName(generateRandomWord());
        product.setDiscountinued(true);
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setSupplier(supplier);
        return product;
    }

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @return - supplier записанный в базу
     */
    public static Supplier createRandomSupplier() {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(generateRandomWord());
        supplier.setPhone(getRandomNumber());
        return supplier;
    }

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @return - случайное число
     */
    public static String getRandomNumber() {
        return Integer.toString(1 + (int) (Math.random() * 10000));
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
}
