package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.dto.SupplierDTO;

import java.util.List;

/**
 * Интерфейс для работы с SupplierServiceImpl
 */
public interface SupplierService {

    SupplierDTO createRandomSupplier();

    SupplierDTO create(SupplierDTO supplierDTO);

    SupplierDTO updateRandomData(SupplierDTO supplierDTO);

    SupplierDTO update(int id, SupplierDTO supplierDTO);

    SupplierDTO getSupplier(int id);

    List<SupplierDTO> getAllSupplier();

    SupplierDTO deleteById(int id);

}