package com.zaraev.epam.javacourses.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
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
import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductResource.class)
@RunWith(SpringRunner.class)
public class ProductResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProduct() throws Exception {
        Product product = createProduct();
        when(productService.getProduct(2)).thenReturn(product);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/product/1"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = createProduct();
        given(productService.create(product)).willReturn(product);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/product")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = createProduct();
        given(productService.update(1, product)).willReturn(product);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/product/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testDeleteByIdProduct() throws Exception {
        Product product = createProduct();
        given(productService.deleteById(2)).willReturn(product);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/product/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    public Product createProduct() {
        Order order = new Order();
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("sdsd");
        customer.setPhone("5852");
        order.setOrderId(1);
        order.setOrderNumber("11");
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(BigDecimal.valueOf(100));
        order.setCustomer(customer);
        Order order2 = new Order();
        Customer customer2 = new Customer();
        customer2.setCustomerId(2);
        customer2.setCustomerName("sddsd");
        customer2.setPhone("58552");
        order2.setOrderId(2);
        order2.setOrderNumber("113");
        order2.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order2.setTotalAmount(BigDecimal.valueOf(1010));
        order2.setCustomer(customer2);
        Set<Order> orders = new HashSet<>();
        orders.add(order);
        orders.add(order2);
        Product product = new Product();
        product.setProductId(2);
        product.setProductName("11");
        product.setUnitPrice(BigDecimal.valueOf(1010));
        product.setDiscountinued(true);
        product.setOrders(orders);
        return product;
    }

}