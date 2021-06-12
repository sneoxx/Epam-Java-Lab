package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.SupplierDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс для работы с контроллером Supplier
 */
@RequestMapping("/supplier")
public interface SupplierResource {

    /**
     * Получение товара по id переданного в запросе
     */
    @GetMapping("/{id}")
    SupplierDTO get(@PathVariable("id") int id);

    /**
     * Получение всех товаров
     */
    @GetMapping
    List<SupplierDTO> getAll();

    /**
     * Создание нового товара из переданного json в запросе
     */
    @PostMapping
    SupplierDTO create(@RequestBody SupplierDTO supplierDTO);

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @PutMapping("/{id}")
    SupplierDTO update(@PathVariable("id") int id, @RequestBody SupplierDTO supplierDTO);

    /**
     * Удаление товара по id переданного в запросе
     */
    @DeleteMapping("/{id}")
    SupplierDTO delete(@PathVariable("id") int id);
}