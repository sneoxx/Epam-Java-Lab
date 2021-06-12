package com.zaraev.epam.javacourses.converter;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертер из CustomerDTO в Customer
 */
@Component
public class CustomerDTOFromCustomerConverter implements Converter<Customer, CustomerDTO> {

    @Override
    public CustomerDTO convert(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setCustomerId(customer.getCustomerId());
        return customerDTO;
    }
}