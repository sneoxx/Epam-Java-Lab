package com.zaraev.epam.javacourses.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.service.CustomerService;
import com.zaraev.epam.javacourses.util.EntityFactoryUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerResource.class)
@RunWith(SpringRunner.class)
public class CustomerResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCustomer() throws Exception {
        Customer customer = EntityFactoryUtil.createRandomCustomer();
        customer.setCustomerName("Michail");
        when(customerService.getCustomer(1)).thenReturn(customer);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value(customer.getCustomerName()));
    }

    @Test()
    public void testGetAllCustomer() throws Exception {
        Customer customer = EntityFactoryUtil.createRandomCustomer();
        customer.setCustomerName("Michail");
        Customer customer2 = EntityFactoryUtil.createRandomCustomer();
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer2);
        when(customerService.getAllCustomer()).thenReturn(customerList);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerName").value(customer.getCustomerName()));
    }

    @Test()
    public void testCreateCustomer() throws Exception {
        Customer customer = EntityFactoryUtil.createRandomCustomer();
        customer.setCustomerName("Michail");
        given(this.customerService.create(customer)).willReturn(customer);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value(customer.getCustomerName()));
    }

    @Test()
    public void testUpdateCustomer() throws Exception {
        Customer customer = EntityFactoryUtil.createRandomCustomer();
        customer.setCustomerName("Michail");
        given(customerService.update(2, customer)).willReturn(customer);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/customer/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value(customer.getCustomerName()));
    }

    @Test()
    public void testDeleteByIdCustomer() throws Exception {
        Customer customer = EntityFactoryUtil.createRandomCustomer();
        customer.setCustomerName("Michail");
        given(customerService.deleteById(2)).willReturn(customer);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/customer/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value(customer.getCustomerName()));
    }

}