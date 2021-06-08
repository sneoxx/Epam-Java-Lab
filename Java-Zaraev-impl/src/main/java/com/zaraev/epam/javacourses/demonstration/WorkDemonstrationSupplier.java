package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.dto.SupplierDTO;
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
        SupplierDTO supplierDTO = supplierService.createRandomSupplier();
        SupplierDTO supplierDTO1 = supplierService.createRandomSupplier();
        supplierService.getSupplier(1);
        System.out.println(supplierService.getAllSupplier());
        supplierService.updateRandomData(supplierDTO);
        supplierService.deleteSupplierWithId(supplierDTO1.getSupplierId());
    }

}