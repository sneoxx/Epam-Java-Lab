package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Customer;

import java.util.List;

/**
 * Интерфейс для работы с CustomerServiceImpl
 */

public interface CustomerService {

    /**
     * Создание и запись в БД рандомного Customer
     *
     * @return - сustomer записанный в базу
     */
    Customer createRandomCustomer();

    /**
     * Создание и запись в БД екземпляра customer
     *
     * @param customer - Экземпляр customer
     * @return - сustomer записанный в базу
     */
    Customer create(Customer customer);

    /**
     * Обновление случайными данными и запись в БД екземпляра Customer
     *
     * @param customer - экземпляр customer, на который необходимо изменить
     * @return - сustomer обновленный в базе
     */
    Customer updateRandomData(Customer customer);

    /**
     * Обновление и запись в БД экземпляра customer
     *
     * @param id       - id экземпляра customer в базе, который необходимо изменить
     * @param customer - экземпляр customer, на который необходимо изменить
     * @return - сustomer обновленный в базе
     */
    Customer update(int id, Customer customer);

    /**
     * Получение Customer из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - сustomer полученный из базы или новый сustomer в случае отстутствия такового id в БД
     */
    Customer getCustomer(int id);

    /**
     * Получение всех Customer из базы
     *
     * @return - коллекция list Customer
     */
    List<Customer> getAllCustomer();

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     * @return - удаленный Customer
     */
    Customer deleteById(int id);

}