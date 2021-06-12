//package com.zaraev.epam.javacourses.service.impl;
//
//import com.zaraev.epam.javacourses.repository.CustomerRepository;
//import com.zaraev.epam.javacourses.service.CustomerService;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
//class CustomerServiceImplTest {
//
//    @Mock
//    CustomerRepository customerRepository;
//
//    @InjectMocks
//    CustomerService customerService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void test() {
//
//           // CustomerService customerService = new CustomerServiceImpl(customerRepository);
//
//        //результат который проверяем
//        Mockito.verify(customerRepository.findById(1), Mockito.times(1)); //метод должен быть вызван 1 раз
//
//        customerService.getCustomer(1);
//
//    }
//}