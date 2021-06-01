package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import com.zaraev.epam.javacourses.service.EService;
import com.zaraev.epam.javacourses.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkDemonstrationSupplier implements EService {

    @Autowired
    private SupplierRepository supplierRepository;// = new SupplierRepository();

    @Autowired
    private SupplierService supplierService;// = new SupplierService();

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