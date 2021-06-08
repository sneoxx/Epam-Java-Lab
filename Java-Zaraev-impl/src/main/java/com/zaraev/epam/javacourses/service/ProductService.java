package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.dto.SupplierDTO;

import java.util.List;

/**
 * Интерфейс для работы с ProductServiceImpl
 */
public interface ProductService {

    ProductDTO createRandomProduct(SupplierDTO supplierDTO);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO updateRandomData(ProductDTO productDTO);

    ProductDTO update(int id, ProductDTO productDTO);

    ProductDTO getProduct(int id);

    List<ProductDTO> getAllProduct();

    void deleteProductWithId(int id);

}