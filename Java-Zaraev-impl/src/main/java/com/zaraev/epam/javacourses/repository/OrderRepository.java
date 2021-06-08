package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Order;

import java.util.List;

/**
 * Интерфейс для работы с OrderRepository
 */

public interface OrderRepository {

    Order create(Order order);

    Order update(Order order);

    Order get(int id);

    List<Order> getAllOrder();

    void delete(int id);

}