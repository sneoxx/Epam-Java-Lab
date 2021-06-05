package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Supplier;

import java.util.List;

public interface ISupplierRepository {

    Supplier create(Supplier product);

    void update(Supplier supplier);

    Supplier getSupplier(int id);

    Supplier getSupplierWithInstance(String supplierName);

    List<Supplier> getAllSupplier();

    void deleteSupplier(Supplier supplier);

    void deleteSupplierWithId(int id);

}