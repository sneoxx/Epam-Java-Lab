package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.converter.CustomerDTOFromCustomerConverter;
import com.zaraev.epam.javacourses.converter.CustomerFromCustomerDTOConverter;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки веб запросов к Customer
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class CustomerResourceImpl implements CustomerResource {

    private final CustomerService customerService;

    private final CustomerDTOFromCustomerConverter customerDTOfromCustomerConverter;

    private final CustomerFromCustomerDTOConverter customerFromCustomerDTOConverter;

    /**
     * Получение клиента по id переданного в запросе
     *
     * @param id - id запроса
     * @return - экземпляр CustomerDTO
     */
    @Override
    public CustomerDTO get(int id) {
        log.info("get() - Получен customer по id {}", id);
        return customerDTOfromCustomerConverter.convert(customerService.getCustomer(id));
    }

    /**
     * Получение всех клиентов
     * @return - коллекция List customerDTO
     */
    @Override
    public List<CustomerDTO> getAll() {
        log.info("getAll()- Получены все customer");
        List<Customer> customerList = customerService.getAllCustomer();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDTOList.add(customerDTOfromCustomerConverter.convert(customer));
        }
        return customerDTOList;
    }

    /**
     * Создание нового клиента из переданного json в запросе
     * @param customerDTO - экземпляр customerDTO для создания
     * @return - созданный customerDTO
     */
    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        log.info("create() - Создан новый customer {}", customerDTO);
        return customerDTOfromCustomerConverter.convert(customerService.create(customerFromCustomerDTOConverter.convert(customerDTO)));
    }

    /**
     * Обновление полей клиента с определенным id из запроса по данным переданным в json запроса
     * @param id - id клиента для обновления
     * @param customerDTO - customerDTO полученный из json запроса
     * @return - обновленный customerDTO
     */
    @Override
    public CustomerDTO update(int id, CustomerDTO customerDTO) {
        log.info("update() - Обновлен customer c id {}", id);
        return customerDTOfromCustomerConverter.convert(customerService.update(id, customerFromCustomerDTOConverter.convert(customerDTO)));
    }

    /**
     * Удаление клиента по id переданного в запросе
     */
    @Override
    public CustomerDTO delete(int id) {
        log.info("delete() - Удален customer с id {}", id);
        return customerDTOfromCustomerConverter.convert(customerService.deleteById(id));
    }
}