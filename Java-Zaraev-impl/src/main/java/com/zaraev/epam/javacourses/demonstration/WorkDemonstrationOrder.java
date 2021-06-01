package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.service.impl.CustomerService;
import com.zaraev.epam.javacourses.service.impl.OrderService;

public class WorkDemonstrationOrder {

    OrderRepository orderRepository = new OrderRepository();
    OrderService orderService = new OrderService();
    CustomerRepository customerRepository = new CustomerRepository();
    CustomerService customerService = new CustomerService();

    /**
     * Метод для демонстрации работы операций CRUD класса Order
     */
    public void testOrder() {
        Customer customer = customerService.createRandomCustomer();
        Customer customer1 = customerService.createRandomCustomer();
        Order order = orderService.createRandomOrder(customer);
        Order order1 = orderService.createRandomOrder(customer1);
        orderRepository.getOrder(1);
        customerService.update(customer);
        orderRepository.deleteOrder(order1);

    }
}