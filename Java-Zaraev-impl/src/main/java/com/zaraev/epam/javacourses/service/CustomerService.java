package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    Customer createRandomCustomer();

    CustomerDTO create(CustomerDTO customerDTO);

    CustomerDTO update(Customer customer);

    CustomerDTO updateCustomerWithId(int id, CustomerDTO customerDTO);

    CustomerDTO getCustomer(int id);

    List<CustomerDTO> getAllCustomer();

    void deleteCustomerWithId(int id);

    CustomerDTO createCustomerDTO(Customer customer);

}