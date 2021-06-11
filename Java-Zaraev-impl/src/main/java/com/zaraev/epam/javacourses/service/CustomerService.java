package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.dto.CustomerDTO;

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
    CustomerDTO createRandomCustomer();

    /**
     * Создание и запись в БД екземпляра customer
     *
     * @param customerDTO - Экземпляр customerDTO
     * @return - сustomerDTO конвертированный из Customer записанного в базу
     */
    CustomerDTO create(CustomerDTO customerDTO);

    /**
     * Обновление случайными данными и запись в БД екземпляра Customer
     *
     * @param customerDTO - экземпляр customer, на который необходимо изменить
     * @return - результат операции сustomerDTO конвертированный из Customer полученного из базы
     */
    CustomerDTO updateRandomData(CustomerDTO customerDTO);

    /**
     * Обновление и запись в БД экземпляра customer
     *
     * @param id          - id экземпляра customer в базе, который необходимо изменить
     * @param customerDTO - экземпляр customer, на который необходимо изменить
     * @return - CustomerDTO конвертированный из обновленного Customer
     */
    CustomerDTO update(int id, CustomerDTO customerDTO);

    /**
     * Получение Customerиз базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полученного Customer
     */
    CustomerDTO getCustomer(int id);

    /**
     * Получение всех Customer из базы
     *
     * @return - коллекция CustomerDTO конвертированная из полученного коллекции Customer
     */
    List<CustomerDTO> getAllCustomer();

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     * @return - CustomerDTO конвертированный из удаленного Customer
     */
    CustomerDTO deleteById(int id);

}