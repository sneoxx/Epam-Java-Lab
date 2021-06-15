package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerResource.class) //аннотация для контролеров(указываем контроллер)
@RunWith(SpringRunner.class)
public class CustomerResourceImplTest {

    @Autowired
    private MockMvc mockMvc; //обращаемся к контроллеру через mockMvc

    @MockBean
    private CustomerService customerService; // сам сервис нас не интересует, мокаем его бин

    //контекст нам делать уже не нужно, потому что бинов у нас нет, а все остальное приходит при помощи @WebMvcTest
    @Test()
    public void test() throws Exception {
        //проверяем поиск по id
        // что
        Customer customer = new Customer();
        customer.setCustomerId(1);
        //задаем поведение - должны вызвать тестируемый метод
        when(customerService.getCustomer(1)).thenReturn(customer); // когда customerService вызывает метод с id, тогда вернуть то то
        // метод perform пытается выполнить что то
        mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/1")) // по какому методу и покакому урл будет обращение, достаточно адреса endpointa
                .andExpect(status().isOk()); //какой статус должен вернуть
    }


    @Test(expected = NestedServletException.class) //сервлет возвращает вложенное исключение
    public void test1() throws Exception {
        //проверяем поиск по id
        // что
        Customer customer = new Customer();
        customer.setCustomerId(1);
        //задаем поведение
        when(customerService.getCustomer(1)).thenThrow(new RuntimeException("My Test"));
        // метод perform пытается выполнить что то
        mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/1")) // по какому методу и покакому урл будет обращение, достаточно адреса endpointa
                .andExpect(status().isOk()); //какой статус должен вернуть
    }
}