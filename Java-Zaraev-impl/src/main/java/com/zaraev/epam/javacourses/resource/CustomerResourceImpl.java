package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс для обработки веб запросов к Customer
 */
@RestController
@Slf4j
public class CustomerResourceImpl implements CustomerResource{

    @Autowired
    private CustomerService customerService;

    /**
     * Получение клиента по id переданного в запросе
     */
    @Override
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public CustomerDTO get(@PathVariable("id") int id) {
        log.info("get() - Получен customer по id {}", id);
        return customerService.getCustomer(id);
    }

    /**
     * Получение всех клиентов
     */
    @Override
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<CustomerDTO> getAll() {
        log.info("getAll()- Получены все customer");
        return customerService.getAllCustomer();
    }

    /**
     * Создание нового клиента из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) {
        log.info("create() - Создан новый customer {}", customerDTO);
        return customerService.create(customerDTO);
    }

    /**
     * Обновление полей клиента из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public CustomerDTO update(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO) {
        log.info("update() - Обновлен customer c id {}", id);
        return customerService.update(id, customerDTO);
    }

    /**
     * Удаление клиента по id переданного в запросе
     */
    @Override
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO delete(@PathVariable("id") int id) {
        log.info("delete() - Удален customer с id {}", id);
        return customerService.deleteById(id);
    }
}