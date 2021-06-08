package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.service.CustomerService;
import com.zaraev.epam.javacourses.service.OrderService;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * Метод для демонстрации работы операций CRUD класса Order
 */
@Slf4j
public class WorkDemonstrationOrder {

    public void test(ApplicationContext context) {
        SupplierService supplierService = context.getBean(SupplierService.class);
        CustomerService customerService = context.getBean(CustomerService.class);
        OrderService orderService = context.getBean(OrderService.class);
        CustomerDTO customer = customerService.createRandomCustomer();
        CustomerDTO customer1 = customerService.createRandomCustomer();
        SupplierDTO supplier = supplierService.createRandomSupplier();
        OrderDTO order = orderService.createRandomOrder(customer, 1);
        OrderDTO order1 = orderService.createRandomOrder(customer1, 1);
        orderService.getOrder(1);
        //orderService.update(1, order);
        customerService.updateRandomData(customer);
        orderService.deleteById(order.getOrderId());
    }

}