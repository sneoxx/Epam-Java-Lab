package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
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
        CustomerDTO customer = customerService.createRandomCustomer();
        CustomerDTO customer1 = customerService.createRandomCustomer();
        SupplierDTO supplier = supplierService.createRandomSupplier();
        OrderDTO order = orderService.createRandomOrder(customer, 1);
        OrderDTO order1 = orderService.createRandomOrder(customer1, 2);
        orderService.getOrder(1);
        //orderService.update(order);
        customerService.updateRandomData(customer);
        orderService.deleteOrderWithId(order1.getOrderId());
    }

}