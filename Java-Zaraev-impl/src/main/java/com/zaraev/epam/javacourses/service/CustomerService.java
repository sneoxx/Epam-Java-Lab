package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Customer;

import java.util.List;

/**
 * Интерфейс для работы с CustomerServiceImpl
 */

public interface CustomerService {

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @return - сustomerDTO конвертированный из Customer записанного в базу
     */
    Customer createRandomCustomer();

    /**
     * Создание и запись в БД екземпляра customer
     *
     * @param customer - Экземпляр customerDTO
     * @return - сustomerDTO конвертированный из Customer записанного в базу
     */
    Customer create(Customer customer);

    /**
     * Обновление случайными данными и запись в БД екземпляра Customer
     *
     * @param customer - экземпляр customer, на который необходимо изменить
     * @return - результат операции сustomerDTO конвертированный из Customer полученного из базы
     */
    Customer updateRandomData(Customer customer);

    /**
     * Обновление и запись в БД экземпляра customer
     *
     * @param id          - id экземпляра customer в базе, который необходимо изменить
     * @param customer - экземпляр customer, на который необходимо изменить
     * @return - CustomerDTO конвертированный из обновленного Customer
     */
    Customer update(int id, Customer customer);

    /**
     * Получение Customer из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полученного Customer
     */
    Customer getCustomer(int id);

    /**
     * Получение всех Customer из базы
     *
     * @return - коллекция CustomerDTO конвертированная из полученного коллекции Customer
     */
    List<Customer> getAllCustomer();

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     * @return - CustomerDTO конвертированный из удаленного Customer
     */
    Customer deleteById(int id);

}