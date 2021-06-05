package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {

    Supplier createRandomSupplier();

    SupplierDTO create(SupplierDTO supplierDTO);

    SupplierDTO update(Supplier supplier);

    SupplierDTO updateSupplierWithId(int id, SupplierDTO supplierDTO);

    SupplierDTO getSupplier(int id);

    List<SupplierDTO> getAllSupplier();

    void deleteSupplierWithId(int id);

    SupplierDTO createSupplierDTO(Supplier supplier);

}