package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Customer;

import java.util.List;

public interface ICustomerRepository {

    Customer create(Customer customer);

    void update(Customer customer);

    Customer getCustomer(int id);

    Customer getCustomerWithInstance(String customerName);

    List<Customer> getAllCustomer();

    void deleteCustomer(Customer customer);

    void deleteCustomerWithId(int id);
}