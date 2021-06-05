package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.IProductRepository;
import com.zaraev.epam.javacourses.service.ProductService;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkDemonstrationProduct {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private Environment environment;

    /**
     *Метод для демонстрации работы операций CRUD класса Product
     */
    public void test() {
        for (String profileName : environment.getActiveProfiles()) {
            log.info("Активный профиль: " + profileName);
        }
        Supplier supplier = supplierService.createRandomSupplier();
        Supplier supplier1 = supplierService.createRandomSupplier();
        Product product = productService.createRandomProduct(supplier);
        Product product1 = productService.createRandomProduct(supplier1);
        productRepository.getProduct(1);
        productService.update(product);
        productRepository.deleteProduct(product1);
    }
}