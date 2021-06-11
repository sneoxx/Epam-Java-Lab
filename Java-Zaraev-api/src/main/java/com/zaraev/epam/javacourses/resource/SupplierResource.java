package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.SupplierDTO;

import java.util.List;

/**
 * Интерфейс для работы с контроллером Supplier
 */
public interface SupplierResource {

    /**
     * Получение товара по id переданного в запросе
     */
    SupplierDTO get(int id);

    /**
     * Получение всех товаров
     */
    List<SupplierDTO> getAll();

    /**
     * Создание нового товара из переданного json в запросе
     */
    SupplierDTO create(SupplierDTO supplierDTO);

    /**
     * Обновление полей товара из переданного json в запросе
     */
    SupplierDTO update(int id, SupplierDTO supplierDTO);

    /**
     * Удаление товара по id переданного в запросе
     */
    SupplierDTO delete(int id);
}