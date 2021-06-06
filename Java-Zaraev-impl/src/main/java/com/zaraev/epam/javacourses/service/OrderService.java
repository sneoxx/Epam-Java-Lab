package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.dto.OrderDTO;

import java.util.List;

/**
 * Интерфейс для работы с OrderServiceImpl
 */
public interface OrderService {

    Order createRandomOrder(Customer customer, Integer id);

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO updateRandomData(Order order);

    OrderDTO update(int id, OrderDTO orderDTO);

    OrderDTO getOrder(int id);

    List<OrderDTO> getAllOrder();

    void deleteOrderWithId(int id);

}