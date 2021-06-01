package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import com.zaraev.epam.javacourses.service.impl.SupplierService;

public class WorkDemonstrationSupplier {

    SupplierRepository supplierRepository = new SupplierRepository();
    SupplierService supplierService = new SupplierService();

    /**
     * Метод для демонстрации работы операций CRUD класса Supplier
     */
    public void testSupplier() {
        Supplier supplier = supplierService.createRandomSupplier();
        Supplier supplier1 = supplierService.createRandomSupplier();
        supplierRepository.getSupplier(1);
        supplierService.update(supplier);
        supplierRepository.deleteSupplier(supplier1);
    }
}