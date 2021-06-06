package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.SupplierService;
import com.zaraev.epam.javacourses.service.impl.SupplierServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * Класс для демонстрации работы операций CRUD класса Supplier
 */
@Slf4j
public class WorkDemonstrationSupplier {

    public void test(ApplicationContext context) {
        SupplierService supplierService = context.getBean(SupplierServiceImpl.class);
        Supplier supplier = supplierService.createRandomSupplier();
        Supplier supplier1 = supplierService.createRandomSupplier();
        supplierService.getSupplier(1);
        supplierService.updateRandomData(supplier);
        supplierService.deleteSupplierWithId(supplier1.getSupplierId());
    }

}