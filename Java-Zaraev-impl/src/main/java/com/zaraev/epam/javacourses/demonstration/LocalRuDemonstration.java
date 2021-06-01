package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.*;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.service.impl.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
public class LocalRuDemonstration {

    @Autowired
    CustomerRepository customerRepository;// = new CustomerRepository();

    @Autowired
    CustomerService customerService;// = new CustomerService();


}
