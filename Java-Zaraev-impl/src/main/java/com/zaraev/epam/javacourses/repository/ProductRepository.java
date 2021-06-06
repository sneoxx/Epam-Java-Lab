package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Product;

import java.util.List;

/**
 * Интерфейс для работы с ProductRepository
 */
public interface ProductRepository {

    Product create(Product product);

    Product update(Product product);

    Product get(int id);

    List<Product> getAllProduct();

    void delete(int id);

}