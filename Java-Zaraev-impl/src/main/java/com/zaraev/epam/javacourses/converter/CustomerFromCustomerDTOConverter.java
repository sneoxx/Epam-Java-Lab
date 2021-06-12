package com.zaraev.epam.javacourses.converter;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертер из Customer в CustomerDTO
 */
@Component
public class CustomerFromCustomerDTOConverter implements Converter<CustomerDTO, Customer> {

    @Override
    public Customer convert(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPhone(customerDTO.getPhone());
        customer.setCustomerId(customerDTO.getCustomerId());
        return customer;
    }
}