package com.zaraev.epam.javacourses.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.SupplierService;
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

    @Test
    public void testGetsupplier() throws Exception {
        Supplier supplier = createSupplier();
        when(supplierService.getSupplier(2)).thenReturn(supplier);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/supplier/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllsupplier() throws Exception {
        Supplier supplier = createSupplier();
        Supplier supplier2 = createSupplier();
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
        Supplier supplier = createSupplier();
        given(this.supplierService.create(supplier)).willReturn(supplier);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/supplier")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public void testUpdateSupplier() throws Exception {
        Supplier supplier = createSupplier();
        given(supplierService.update(1, supplier)).willReturn(supplier);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/supplier/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public void testDeleteByIdsupplier() throws Exception {
        Supplier supplier = createSupplier();
        given(supplierService.deleteById(1)).willReturn(supplier);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/supplier/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    public Supplier createSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1);
        supplier.setCompanyName("11");
        supplier.setPhone("dfdf");
        return supplier;
    }
}