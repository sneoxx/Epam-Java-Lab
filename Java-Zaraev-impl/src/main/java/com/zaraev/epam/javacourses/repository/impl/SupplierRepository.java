package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Supplier;

import java.util.List;

/**
 * Интерфейс для работы с SupplierRepository
 */
public interface SupplierRepository {

    Supplier create(Supplier product);

    Supplier update(Supplier supplier);

    Supplier get(int id);

    List<Supplier> getAllSupplier();

    void delete(int id);

}