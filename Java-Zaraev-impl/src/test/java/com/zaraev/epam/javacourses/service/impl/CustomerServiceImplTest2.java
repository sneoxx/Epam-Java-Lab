package com.zaraev.epam.javacourses.service.impl;


import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest // поднимет весь контекст прилы, поднимать дополнительно ничего не надо. Но тяжеловесно. Но если все тесты помечены этой аннотацией поднимет один раз и будет переиспользовать
// но если есть @MockBean или @InjectMocks, будет переподнимать контекст и тесты будут идти долго
@RunWith(SpringRunner.class)
public class CustomerServiceImplTest2 {

    @Autowired
    CustomerService customerService;

    @Test
    public void test() {
        //given
        Customer customer = new Customer();
        //when
        customerService.create(customer);
        Customer createCustomer = customerService.getCustomer(customer.getCustomerId());
        //then - проверяем работу БД - совпадет ли имя у созданной сущности и сущности после сохранения ее в бд
        Assert.assertEquals(customer.getCustomerName(), createCustomer.getCustomerName());
    }
}