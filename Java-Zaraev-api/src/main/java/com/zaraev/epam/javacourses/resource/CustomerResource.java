package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.CustomerDTO;

import java.util.List;

/**
 * Интерфейс для работы с контроллером Customer
 */
public interface CustomerResource {

    /**
     * Получение клиента по id переданного в запросе
     */
    CustomerDTO get(int id);

    /**
     * Получение всех клиентов
     */
    List<CustomerDTO> getAll();

    /**
     * Создание нового клиента из переданного json в запросе
     */
    CustomerDTO create(CustomerDTO customerDTO);


    /**
     * Обновление полей клиента из переданного json в запросе
     */
    CustomerDTO update(int id, CustomerDTO customerDTO);

    /**
     * Удаление клиента по id переданного в запросе
     */
    CustomerDTO delete(int id);
}