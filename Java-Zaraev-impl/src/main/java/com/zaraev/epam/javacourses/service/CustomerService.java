package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.dto.CustomerDTO;

import java.util.List;

/**
 * Интерфейс для работы с CustomerServiceImpl
 */
public interface CustomerService {

    Customer createRandomCustomer();

    CustomerDTO create(CustomerDTO customerDTO);

    CustomerDTO updateRandomData(Customer customer);

    CustomerDTO update(int id, CustomerDTO customerDTO);

    CustomerDTO getCustomer(int id);

    List<CustomerDTO> getAllCustomer();

    void deleteCustomerWithId(int id);

}