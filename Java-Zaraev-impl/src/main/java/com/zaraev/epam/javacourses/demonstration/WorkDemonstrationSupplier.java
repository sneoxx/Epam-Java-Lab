package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.ServiceEntity;

public class WorkDemonstrationSupplier {

    ServiceEntity serviceEntity = new ServiceEntity();

      /**
     * Метод для демонстрации работы операцй CRUD класса Supplier
     */
    public void testSupplier() {
        Supplier supplier = serviceEntity.createSupplier();
        Supplier supplier1 = serviceEntity.createSupplier();
        serviceEntity.getSupplier(1);
        serviceEntity.updateSupplier(supplier);
        serviceEntity.deleteSupplier(supplier1);
    }
}