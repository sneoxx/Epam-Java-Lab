package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.ISupplierRepository;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WorkDemonstrationSupplier  {

    @Autowired
    private ISupplierRepository supplierRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private Environment environment;

    /**
     * Метод для демонстрации работы операций CRUD класса Supplier
     */
    public void test() {
        for (String profileName : environment.getActiveProfiles()) {
            log.info("Активный профиль: " + profileName);
        }
        Supplier supplier = supplierService.createRandomSupplier();
        Supplier supplier1 = supplierService.createRandomSupplier();
        supplierRepository.getSupplier(1);
        supplierService.update(supplier);
        supplierRepository.deleteSupplier(supplier1);
    }
}