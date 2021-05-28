package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.ServiceEntity;

public class WorkDemonstrationProduct {
    ServiceEntity serviceEntity = new ServiceEntity();

    /**
     *Метод для демонстрации работы операций CRUD класса Product
     */
    public void testProduct() {
        Supplier supplier = serviceEntity.createSupplier();
        Supplier supplier1 = serviceEntity.createSupplier();
        Product product = serviceEntity.createProduct(supplier);
        Product product1 = serviceEntity.createProduct(supplier1);
        serviceEntity.getProduct(1);
        serviceEntity.updateProduct(product);
        serviceEntity.deleteProduct(product1);
    }
}
