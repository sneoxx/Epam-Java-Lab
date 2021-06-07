package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.ProductService;
import com.zaraev.epam.javacourses.service.SupplierService;
import com.zaraev.epam.javacourses.service.impl.ProductServiceImpl;
import com.zaraev.epam.javacourses.service.impl.SupplierServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * Метод для демонстрации работы операций CRUD класса Product
 */
@Slf4j
public class WorkDemonstrationProduct {

    public void test(ApplicationContext context) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        SupplierService supplierService = context.getBean(SupplierServiceImpl.class);
        Supplier supplier = supplierService.createRandomSupplier();
        Supplier supplier1 = supplierService.createRandomSupplier();
        Product product = productService.createRandomProduct(supplier);
        Product product1 = productService.createRandomProduct(supplier1);
        productService.getProduct(1);
        productService.updateRandomData(product);
        productService.deleteProductWithId(product1.getProductId());
    }


}