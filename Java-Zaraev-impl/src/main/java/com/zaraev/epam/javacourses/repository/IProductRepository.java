package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Product;

import java.util.List;

public interface IProductRepository {

    Product create(Product product);

    void update(Product product);

    Product getProduct(int id);

    Product getProductWithInstance(String productName);

    List<Product> getAllProduct();

    void deleteProduct(Product product);

    void deleteProductWithId(int id);

}