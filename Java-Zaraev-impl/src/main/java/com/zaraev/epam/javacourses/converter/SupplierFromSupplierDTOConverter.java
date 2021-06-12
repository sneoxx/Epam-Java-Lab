package com.zaraev.epam.javacourses.converter;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертер из SupplierDTO в Supplier
 */
@Component
public class SupplierFromSupplierDTOConverter implements Converter<SupplierDTO, Supplier> {

    @Override
    public Supplier convert(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierDTO.getSupplierId());
        supplier.setCompanyName(supplierDTO.getCompanyName());
        supplier.setPhone(supplierDTO.getPhone());
        return supplier;
    }
}