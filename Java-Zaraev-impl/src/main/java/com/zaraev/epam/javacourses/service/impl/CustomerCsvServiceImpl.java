package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.repository.CustomerCsvRepository;
import com.zaraev.epam.javacourses.service.CustomerCsvService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerCsvServiceImpl implements CustomerCsvService {

    private final CustomerCsvRepository customerCsvRepository;

    @Override
    public List<Customer> getAllCustomer() {

        List<Customer> customerList = new ArrayList<>();

        List<Order> orders = new ArrayList<>();
        //create dummy users
 //       customerList.add(new CustomerCsv(1111, "Jack Lee", "jack@example.com", orders));
  //      customerList.add(new CustomerCsv(2222, "Jovan Srovoki", "jovan@srovoki.me", orders));
  //      customerList.add(new CustomerCsv(3333, "Atta", "atta@gmail.com", orders));
        customerList = customerCsvRepository.findAll();
        return customerList;
      //  return customerList;
       // return customerCsvRepository.findAll(Sort.by(Sort.Direction.ASC, "Id"));
    }

}
