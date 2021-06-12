package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс для работы с контроллером Order
 */
@RequestMapping("/order")
public interface OrderResource {

    /**
     * Получение товара по id переданного в запросе
     */
    @GetMapping("/{id}")
    OrderDTO get(@PathVariable("id") int id);

    /**
     * Получение всех товаров
     */
    @GetMapping
    List<OrderDTO> getAll();

    /**
     * Создание нового товара из переданного json в запросе
     */
    @PostMapping
    OrderDTO create(@RequestBody OrderDTO orderDTO);

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @PutMapping("/{id}")
    OrderDTO update(@PathVariable("id") int id, @RequestBody OrderDTO orderDTO);

    /**
     * Удаление товара по id переданного в запросе
     */
    @DeleteMapping("/{id}")
    OrderDTO delete(@PathVariable("id") int id);
}