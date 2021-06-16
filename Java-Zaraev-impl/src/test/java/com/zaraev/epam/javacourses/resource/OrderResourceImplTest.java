package com.zaraev.epam.javacourses.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.service.OrderService;
import com.zaraev.epam.javacourses.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderResource.class)
@RunWith(SpringRunner.class)
public class OrderResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetOrder() throws Exception {
        Order order = createOrder();
        when(orderService.getOrder(1)).thenReturn(order);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/order/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrder() throws Exception {
        Order order = createOrder();
        Order order2 = createOrder();
        List<Order> OrderList = new ArrayList<>();
        OrderList.add(order);
        OrderList.add(order2);
        when(orderService.getAllOrder()).thenReturn(OrderList);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/order"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = createOrder();
        given(this.orderService.create(order)).willReturn(order);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/order")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order order = createOrder();
        given(this.orderService.update(1, order)).willReturn(order);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/order/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testDeleteByIdOrder() throws Exception {
        Order order = createOrder();
        given(orderService.deleteById(1)).willReturn(order);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/order/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    public Order createOrder() {
        Order order = new Order();
        Customer customer = new Customer();
        customer.setCustomerId(1);
        order.setOrderId(1);
        order.setOrderNumber("11");
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(BigDecimal.valueOf(100));
        order.setCustomer(customer);
        return order;
    }
}