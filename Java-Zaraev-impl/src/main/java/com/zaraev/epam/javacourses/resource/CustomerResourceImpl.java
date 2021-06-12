package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
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

    private final ConversionService conversionService;

    /**
     * Получение клиента по id переданного в запросе
     *
     * @param id - id из запроса
     * @return - экземпляр CustomerDTO
     */
    @Override
    public CustomerDTO get(int id) {
        Customer customerResult = customerService.getCustomer(id);
        CustomerDTO customerDTOCheck = conversionService.convert(customerResult, CustomerDTO.class);
        log.info("get() - Получен customer: {}", customerDTOCheck);
        return customerDTOCheck;
    }

    /**
     * Получение всех клиентов
     *
     * @return - коллекция List customerDTO
     */
    @Override
    public List<CustomerDTO> getAll() {
        List<Customer> customerList = customerService.getAllCustomer();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDTOList.add(conversionService.convert(customer, CustomerDTO.class));
        }
        log.info("getAll()- Получены все customer");
        return customerDTOList;
    }

    /**
     * Создание нового клиента из переданного json в запросе
     *
     * @param customerDTO - экземпляр customerDTO для создания
     * @return - созданный customerDTO
     */
    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customerConvert = conversionService.convert(customerDTO, Customer.class);
        Customer customerResult = customerService.create(customerConvert);
        CustomerDTO customerDTOCheck = conversionService.convert(customerResult, CustomerDTO.class);
        log.info("create() - Создан новый customer {}", customerDTOCheck);
        return customerDTOCheck;
    }

    /**
     * Обновление полей клиента с определенным id из запроса по данным переданным в json запроса
     *
     * @param id          - id клиента для обновления
     * @param customerDTO - customerDTO полученный из json запроса
     * @return - обновленный customerDTO
     */
    @Override
    public CustomerDTO update(int id, CustomerDTO customerDTO) {
        Customer customerConvert = conversionService.convert(customerDTO, Customer.class);
        Customer customerResult = customerService.update(id, customerConvert);
        CustomerDTO customerDTOCheck = conversionService.convert(customerResult, CustomerDTO.class);
        log.info("update() - Обновлен customer: {}", customerDTOCheck);
        return customerDTOCheck;
    }

    /**
     * Удаление заказа по id переданного в запросе
     *
     * @param id - id удаляемого объекта
     * @return - удаленный объект CustomerDTO
     */
    @Override
    public CustomerDTO delete(int id) {
        Customer customerResult = customerService.deleteById(id);
        CustomerDTO customerDTO = conversionService.convert(customerResult, CustomerDTO.class);
        log.info("delete() - Удален customer: {}", customerDTO);
        return customerDTO;
    }
}