package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс для работы с контроллером Product
 */
@RequestMapping("/product")
public interface ProductResource {

    /**
     * Получение товара по id переданного в запросе
     */
    @GetMapping("/{id}")
    ProductDTO get(@PathVariable("id") int id);

    /**
     * Получение всех товаров
     */
    @GetMapping
    List<ProductDTO> getAll();

    /**
     * Создание нового товара из переданного json в запросе
     */
    @PostMapping
    ProductDTO create(@RequestBody ProductDTO productDTO);

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @PutMapping("/{id}")
    ProductDTO update(@PathVariable("id") int id, @RequestBody ProductDTO productDTO);

    /**
     * Удаление товара по id переданного в запросе
     */
    @DeleteMapping("/{id}")
    ProductDTO delete(@PathVariable("id") int id);
}