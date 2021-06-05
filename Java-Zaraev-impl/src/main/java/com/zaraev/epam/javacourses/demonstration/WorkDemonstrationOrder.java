package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.impl.IOrderRepository;
import com.zaraev.epam.javacourses.service.CustomerService;
import com.zaraev.epam.javacourses.service.OrderService;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkDemonstrationOrder {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private OrderService orderServiceImpl;

    @Autowired
    private CustomerService customerServiceImpl;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private Environment environment;

    /**
     * Метод для демонстрации работы операций CRUD класса Order
     */
    public void test() {
        for (String profileName : environment.getActiveProfiles()) {
            log.info("Активный профиль: " + profileName);
        }
        Customer customer = customerServiceImpl.createRandomCustomer();
        Customer customer1 = customerServiceImpl.createRandomCustomer();
        Supplier supplier = supplierService.createRandomSupplier();
        Order order = orderServiceImpl.createRandomOrder(customer, 1);
        Order order1 = orderServiceImpl.createRandomOrder(customer1,2);
        orderRepository.getOrder(1);
        customerServiceImpl.update(customer);
        orderRepository.deleteOrder(order1);
    }
}