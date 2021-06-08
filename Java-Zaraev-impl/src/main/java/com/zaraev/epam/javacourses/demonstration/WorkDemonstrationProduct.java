package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.service.ProductService;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * Метод для демонстрации работы операций CRUD класса Product
 */
@Slf4j
public class WorkDemonstrationProduct {

    public void test(ApplicationContext context) {
        ProductService productService = context.getBean(ProductService.class);
        SupplierService supplierService = context.getBean(SupplierService.class);
        SupplierDTO supplierDTO = supplierService.createRandomSupplier();
        SupplierDTO supplierDTO1 = supplierService.createRandomSupplier();
        ProductDTO productDTO = productService.createRandomProduct(supplierDTO);
        ProductDTO productDTO1 = productService.createRandomProduct(supplierDTO1);
        productService.getProduct(1);
        productService.updateRandomData(productDTO);
        productService.deleteById(productDTO1.getProductId());
    }


}