package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Customer;

import java.util.List;
import java.util.Locale;

/**
 * Интерфейс для работы с CustomerRepository
 */
public interface CustomerRepository {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Customer get(int id);

    List<Customer> getAllCustomer();

    void delete(int id);

    void setLocale(Locale locale);

}