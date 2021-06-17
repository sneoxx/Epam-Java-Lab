package com.zaraev.epam.javacourses.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.SupplierService;
import com.zaraev.epam.javacourses.util.EntityFactory;
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

@WebMvcTest(SupplierResource.class)
@RunWith(SpringRunner.class)
public class SupplierResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    @Autowired
    private ObjectMapper objectMapper;

    private EntityFactory entityFactory = new EntityFactory();

    @Test
    public void testGetsupplier() throws Exception {
        Supplier supplier = entityFactory.createRandomSupplier();
        supplier.setCompanyName("Rabbit");
        when(supplierService.getSupplier(1)).thenReturn(supplier);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/supplier/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Rabbit"));
    }

    @Test
    public void testGetAllsupplier() throws Exception {
        Supplier supplier = entityFactory.createRandomSupplier();
        Supplier supplier2 = entityFactory.createRandomSupplier();
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(supplier);
        supplierList.add(supplier2);
        when(supplierService.getAllSupplier()).thenReturn(supplierList);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/supplier"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatesupplier() throws Exception {
        Supplier supplier = entityFactory.createRandomSupplier();
        supplier.setCompanyName("Rabbit");
        given(this.supplierService.create(supplier)).willReturn(supplier);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/supplier")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Rabbit"));
    }

    @Test
    public void testUpdateSupplier() throws Exception {
        Supplier supplier = entityFactory.createRandomSupplier();
        supplier.setCompanyName("Rabbit");
        given(supplierService.update(1, supplier)).willReturn(supplier);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/supplier/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Rabbit"));
    }

    @Test
    public void testDeleteByIdsupplier() throws Exception {
        Supplier supplier = entityFactory.createRandomSupplier();
        supplier.setCompanyName("Rabbit");
        given(supplierService.deleteById(1)).willReturn(supplier);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/supplier/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Rabbit"));
    }

}