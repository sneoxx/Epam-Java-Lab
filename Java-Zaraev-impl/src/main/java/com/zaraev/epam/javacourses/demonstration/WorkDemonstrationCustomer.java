package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.repository.ICustomerRepository;
import com.zaraev.epam.javacourses.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class WorkDemonstrationCustomer {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Environment environment;

    /**
     * Метод для демонстрации работы операций CRUD класса Customer
     */
    public void test() {
        for (String profileName : environment.getActiveProfiles()) {
            log.info("Активный профиль: " + profileName);
        }

        Customer customer = customerService.createRandomCustomer();
        Customer customer1 = customerService.createRandomCustomer();
        customerRepository.getCustomer(1);
        customerService.update(customer);
        customerRepository.deleteCustomer(customer1);
    }
}