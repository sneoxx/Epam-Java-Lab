package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;

import java.util.List;

/**
 * Интерфейс для работы с OrderServiceImpl
 */
public interface OrderService {

    /**
     * Создание и запись в БД рандомного Order
     *
     * @param customer - экземпляр customer
     * @return - order записанный в базу
     */
    Order createRandomOrder(Customer customer, Integer id);

    /**
     * Создание и запись в БД экземпляра order
     *
     * @param order - Экземпляр order
     * @return - order записанный в базу
     */
    Order create(Order order);

    /**
     * Обновление случайными данными и запись в БД екземпляра Order
     *
     * @param order - экземпляр order, на который необходимо изменить
     * @return - order обновленный в базе
     */
    Order updateRandomData(Order order);

    /**
     * Обновление и запись в БД  order
     *
     * @param id       - id экземпляра order в базе, который необходимо изменить
     * @param order - экземпляр order, на который необходимо изменить
     * @return - order обновленный в базе
     */
    Order update(int id, Order order);

    /**
     * Получение Order из базы
     *
     * @param id - id Order, которое необходимло получить
     * @return - order полученный из базы или новый order в случае отстутствия такового id в БД
     */
    Order getOrder(int id);

    /**
     * Получение всех Order из базы
     *
     * @return - коллекция list Customer
     */
    List<Order> getAllOrder();

    /**
     * Удаление Order из базы по id
     *
     * @param id - id Order для удаления
     * @return - удаленный Order
     */
    Order deleteById(int id);

}