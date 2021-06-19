package com.zaraev.epam.javacourses.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.ProductService;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        Supplier supplier = EntityFactoryUtil.createRandomSupplier();
        Product product = EntityFactoryUtil.createRandomProduct(supplier);
        product.setProductName("Car");
        when(productService.getProduct(1)).thenReturn(product);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/product/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(product.getProductName()));
    }

    @Test
    public void testCreateProduct() throws Exception {
        Supplier supplier = EntityFactoryUtil.createRandomSupplier();
        Product product = EntityFactoryUtil.createRandomProduct(supplier);
        product.setProductName("Car");
        given(productService.create(product)).willReturn(product);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/product")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(product.getProductName()));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Supplier supplier = EntityFactoryUtil.createRandomSupplier();
        Product product = EntityFactoryUtil.createRandomProduct(supplier);
        product.setProductName("Car");
        given(productService.update(1, product)).willReturn(product);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/product/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(product.getProductName()));
    }

    @Test
    public void testDeleteByIdProduct() throws Exception {
        Supplier supplier = EntityFactoryUtil.createRandomSupplier();
        Product product = EntityFactoryUtil.createRandomProduct(supplier);
        product.setProductName("Car");
        given(productService.deleteById(1)).willReturn(product);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/product/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(product.getProductName()));
    }

}