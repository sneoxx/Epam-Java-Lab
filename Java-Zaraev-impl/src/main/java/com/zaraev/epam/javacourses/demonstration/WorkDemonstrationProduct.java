package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.service.impl.ProductService;
import com.zaraev.epam.javacourses.service.impl.SupplierService;

public class WorkDemonstrationProduct {

    ProductRepository productRepository = new ProductRepository();

    ProductService productService = new ProductService();

    SupplierService supplierService = new SupplierService();

    /**
     *Метод для демонстрации работы операций CRUD класса Product
     */
    public void testProduct() {
        Supplier supplier = supplierService.createRandomSupplier();
        Supplier supplier1 = supplierService.createRandomSupplier();
        Product product = productService.createRandomProduct(supplier);
        Product product1 = productService.createRandomProduct(supplier1);
        productRepository.getProduct(1);
        productService.update(product);
        productRepository.deleteProduct(product1);
    }
}