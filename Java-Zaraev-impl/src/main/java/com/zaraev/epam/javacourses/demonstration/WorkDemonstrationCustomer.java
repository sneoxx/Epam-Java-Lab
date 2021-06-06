package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.service.CustomerService;
import com.zaraev.epam.javacourses.service.impl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * Метод для демонстрации работы операций CRUD класса Customer
 */
@Slf4j
public class WorkDemonstrationCustomer {

    private CustomerRepository customerRepository;

    private CustomerService customerService;

    public void test(ApplicationContext context) {
        CustomerService customerService = context.getBean(CustomerServiceImpl.class);
        Customer customer = customerService.createRandomCustomer();
        Customer customer1 = customerService.createRandomCustomer();
        customerService.getCustomer(1);
        customerService.updateRandomData(customer);
        customerService.deleteCustomerWithId(customer1.getCustomerId());
    }
}