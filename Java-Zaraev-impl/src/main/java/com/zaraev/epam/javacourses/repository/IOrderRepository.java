package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Order;

import java.util.List;

public interface IOrderRepository {

    Order create(Order order);

    void update(Order order);

    Order getOrder(int id);

    Order getOrderWithInstance(String orderName);

    List<Order> getAllOrder();

    void deleteOrder(Order order);

    void deleteOrderWithId(int id);

}