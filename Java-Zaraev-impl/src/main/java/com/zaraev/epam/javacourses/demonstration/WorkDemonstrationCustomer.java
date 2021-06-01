package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.service.EService;
import com.zaraev.epam.javacourses.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkDemonstrationCustomer implements EService {

    @Autowired
    CustomerRepository customerRepository;// = new CustomerRepository();

    @Autowired
    CustomerService customerService;// = new CustomerService();

    /**
     * Метод для демонстрации работы операций CRUD класса Customer
     */
    public void testCustomer() {
        Customer customer = customerService.createRandomCustomer();
        Customer customer1 = customerService.createRandomCustomer();
        customerRepository.getCustomer(1);
        customerService.update(customer);
        customerRepository.deleteCustomer(customer1);
    }
}