package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.dto.OrderDTO;

import java.util.List;

/**
 * Интерфейс для работы с OrderServiceImpl
 */
public interface OrderService {

    OrderDTO createRandomOrder(CustomerDTO customerDTO, Integer id);

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO updateRandomData(Order order);

    OrderDTO update(int id, OrderDTO orderDTO);

    OrderDTO getOrder(int id);

    List<OrderDTO> getAllOrder();

    OrderDTO deleteById(int id);

}