package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.dto.CustomerDTO;

import java.util.List;

/**
 * Интерфейс для работы с CustomerServiceImpl
 */

public interface CustomerService {

    CustomerDTO createRandomCustomer();

    CustomerDTO create(CustomerDTO customerDTO);

    CustomerDTO updateRandomData(CustomerDTO customer);

    CustomerDTO update(int id, CustomerDTO customerDTO);

    CustomerDTO getCustomer(int id);

    List<CustomerDTO> getAllCustomer();

    void deleteById(int id);

}