package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.CustomerService;
import com.zaraev.epam.javacourses.service.OrderService;
import com.zaraev.epam.javacourses.service.SupplierService;
import com.zaraev.epam.javacourses.service.impl.CustomerServiceImpl;
import com.zaraev.epam.javacourses.service.impl.OrderServiceImpl;
import com.zaraev.epam.javacourses.service.impl.SupplierServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * Метод для демонстрации работы операций CRUD класса Order
 */
@Slf4j
public class WorkDemonstrationOrder {

    public void test(ApplicationContext context) {
        SupplierService supplierService = context.getBean(SupplierServiceImpl.class);
        CustomerService customerService = context.getBean(CustomerServiceImpl.class);
        OrderService orderService = context.getBean(OrderServiceImpl.class);
        Customer customer = customerService.createRandomCustomer();
        Customer customer1 = customerService.createRandomCustomer();
        Supplier supplier = supplierService.createRandomSupplier();
        Order order = orderService.createRandomOrder(customer, 1);
        Order order1 = orderService.createRandomOrder(customer1, 2);
        orderService.getOrder(1);
        //orderService.update(order);
        customerService.updateRandomData(customer);
        orderService.deleteOrderWithId(order1.getOrderId());
    }

}