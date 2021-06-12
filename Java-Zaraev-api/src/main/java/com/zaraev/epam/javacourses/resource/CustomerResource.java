package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс для работы с контроллером Customer
 */
@RequestMapping("/customer")
public interface CustomerResource {

    /**
     * Получение клиента по id переданного в запросе
     */
    @GetMapping("/{id}")
    CustomerDTO get(@PathVariable("id") int id);

    /**
     * Получение всех клиентов
     */
    @GetMapping
    List<CustomerDTO> getAll();

    /**
     * Создание нового клиента из переданного json в запросе
     */
    @PostMapping
    CustomerDTO create(@RequestBody CustomerDTO customerDTO);

    /**
     * Обновление полей клиента из переданного json в запросе
     */
    @PutMapping("/{id}")
    CustomerDTO update(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO);

    /**
     * Удаление клиента по id переданного в запросе
     */
    @DeleteMapping("/{id}")
    CustomerDTO delete(@PathVariable("id") int id);
}