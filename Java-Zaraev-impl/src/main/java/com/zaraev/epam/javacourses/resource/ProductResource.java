package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.ProductDTO;

import java.util.List;

/**
 * Интерфейс для работы с сервлетами
 */
public interface ProductResource {

    /**
     * Получение товара по id переданного в запросе
     */
    ProductDTO get(int id);

    /**
     * Получение всех товаров
     */
    List<ProductDTO> getAll();

    /**
     * Создание нового товара из переданного json в запросе
     */
    ProductDTO create(ProductDTO productDTO);

    /**
     * Обновление полей товара из переданного json в запросе
     */
    ProductDTO update(int id, ProductDTO productDTO);

    /**
     * Удаление товара по id переданного в запросе
     */
    ProductDTO delete(int id);
}