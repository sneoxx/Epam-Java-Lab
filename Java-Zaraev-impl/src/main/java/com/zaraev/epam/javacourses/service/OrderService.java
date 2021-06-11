package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.dto.OrderDTO;

import java.util.List;

/**
 * Интерфейс для работы с OrderServiceImpl
 */
public interface OrderService {

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @param customerDTO - экземпляр customerDTO
     * @return - OrderDTO конвертированный из Order записанного в базу
     */
    OrderDTO createRandomOrder(CustomerDTO customerDTO, Integer id);

    /**
     * Создание и запись в БД экземпляра order
     *
     * @param orderDTO - Экземпляр orderDTO
     * @return - OrderDTO конвертированный из Order записанного в базу
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * Обновление случайными данными и запись в БД екземпляра Order
     *
     * @param order - экземпляр order, на который необходимо изменить
     * @return - - результат операции OrderDTO конвертированный из Order полученного из базы
     */
    OrderDTO updateRandomData(Order order);

    /**
     * Обновление и запись в БД  order
     *
     * @param id       - id экземпляра order в базе, который необходимо изменить
     * @param orderDTO - экземпляр orderDTO, на который необходимо изменить
     * @return - OrderDTO конвертированный из обновленного Order
     */
    OrderDTO update(int id, OrderDTO orderDTO);

    /**
     * Получение Order из базы
     *
     * @param id - id Order, которое необходимло получить
     * @return - OrderDTO созданный из полученного Order
     */
    OrderDTO getOrder(int id);

    /**
     * Получение всех Order из базы
     *
     * @return - коллекция CustomerDTO конвертированная из полученной коллекции Customer
     */
    List<OrderDTO> getAllOrder();

    /**
     * Удаление Order из базы по id
     *
     * @param id - id Order для удаления
     * @return - OrderDTO конвертированный из удаленного Order
     */
    OrderDTO deleteById(int id);

}