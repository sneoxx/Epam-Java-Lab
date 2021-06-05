package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    Product createRandomProduct(Supplier supplier);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(Product product);

    ProductDTO updateProductWithId(int id, ProductDTO productDTO);

    ProductDTO getProduct(int id);

    List<ProductDTO> getAllProduct();

    void deleteProductWithId(int id);

    ProductDTO createProductDTO(Product product);

}