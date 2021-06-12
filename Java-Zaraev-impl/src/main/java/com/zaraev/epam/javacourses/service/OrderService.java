package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;

import java.util.List;

/**
 * Интерфейс для работы с OrderServiceImpl
 */
public interface OrderService {

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @param customer - экземпляр customerDTO
     * @return - OrderDTO конвертированный из Order записанного в базу
     */
    Order createRandomOrder(Customer customer, Integer id);

    /**
     * Создание и запись в БД экземпляра order
     *
     * @param order - Экземпляр orderDTO
     * @return - OrderDTO конвертированный из Order записанного в базу
     */
    Order create(Order order);

    /**
     * Обновление случайными данными и запись в БД екземпляра Order
     *
     * @param order - экземпляр order, на который необходимо изменить
     * @return - - результат операции OrderDTO конвертированный из Order полученного из базы
     */
    Order updateRandomData(Order order);

    /**
     * Обновление и запись в БД  order
     *
     * @param id       - id экземпляра order в базе, который необходимо изменить
     * @param order - экземпляр orderDTO, на который необходимо изменить
     * @return - OrderDTO конвертированный из обновленного Order
     */
    Order update(int id, Order order);

    /**
     * Получение Order из базы
     *
     * @param id - id Order, которое необходимло получить
     * @return - OrderDTO созданный из полученного Order
     */
    Order getOrder(int id);

    /**
     * Получение всех Order из базы
     *
     * @return - коллекция CustomerDTO конвертированная из полученной коллекции Customer
     */
    List<Order> getAllOrder();

    /**
     * Удаление Order из базы по id
     *
     * @param id - id Order для удаления
     * @return - OrderDTO конвертированный из удаленного Order
     */
    Order deleteById(int id);

}