package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.OrderDTO;

import java.util.List;

/**
 * Интерфейс для работы с контроллером Order
 */
public interface OrderResource {

    /**
     * Получение товара по id переданного в запросе
     */
    OrderDTO get(int id);

    /**
     * Получение всех товаров
     */
    List<OrderDTO> getAll();

    /**
     * Создание нового товара из переданного json в запросе
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * Обновление полей товара из переданного json в запросе
     */
    OrderDTO update(int id, OrderDTO orderDTO);

    /**
     * Удаление товара по id переданного в запросе
     */
    OrderDTO delete(int id);
}