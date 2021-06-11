package com.zaraev.epam.javacourses.service.impl;


import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.helper.ServiceHelper;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с CustomerRepository
 */
@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final ServiceHelper serviceHelper = new ServiceHelper();

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @return - сustomerDTO конвертированный из Customer записанного в базу
     */
    @Override
    public CustomerDTO createRandomCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(serviceHelper.generateRandomWord());
        customer.setPhone(serviceHelper.getRandomNumber());
        customerRepository.saveAndFlush(customer);
        Customer customerCheck = customerRepository.getOne(customer.getCustomerId());
        log.debug("createRandomCustomer() Объект customer успешно записан в БД: {} ", customerCheck);
        return serviceHelper.createDTOFromCustomer(customerCheck);
    }

    /**
     * Создание и запись в БД екземпляра customer
     *
     * @param customerDTO - Экземпляр customerDTO
     * @return - сustomerDTO конвертированный из Customer записанного в базу
     */
    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPhone(customerDTO.getPhone());
        customerRepository.saveAndFlush(customer);
        Customer customerCheck = customerRepository.getOne(customer.getCustomerId());
        log.debug("create() Объект customer успешно записан в БД: {} ", customerCheck);
        return serviceHelper.createDTOFromCustomer(customerCheck);
    }

    /**
     * Обновление случайными данными и запись в БД екземпляра Customer
     *
     * @param customerDTO - экземпляр customer, на который необходимо изменить
     * @return - результат операции сustomerDTO конвертированный из Customer полученного из базы
     */
    @Override
    public CustomerDTO updateRandomData(CustomerDTO customerDTO) {
        customerDTO.setCustomerName(customerDTO.getCustomerName() + "+" + serviceHelper.generateRandomWord());
        customerRepository.saveAndFlush(serviceHelper.createCustomerFromDTO(customerDTO));
        Customer customerCheck = customerRepository.getOne(customerDTO.getCustomerId());
        log.debug("updateRandomData() Объект customer успешно обновлен в БД: {} ", customerCheck);
        return serviceHelper.createDTOFromCustomer(customerCheck);
    }

    /**
     * Обновление и запись в БД экземпляра customer
     *
     * @param id          - id экземпляра customer в базе, который необходимо изменить
     * @param customerDTO - экземпляр customer, на который необходимо изменить
     * @return - CustomerDTO конвертированный из обновленного Customer
     */
    @Override
    public CustomerDTO update(int id, CustomerDTO customerDTO) {
        Customer updateCustomer = customerRepository.getOne(id);
        updateCustomer.setCustomerName(customerDTO.getCustomerName());
        updateCustomer.setPhone(customerDTO.getPhone());
        customerRepository.saveAndFlush(updateCustomer);
        Customer customerCheck = customerRepository.getOne(updateCustomer.getCustomerId());
        log.debug("updateRandomData() Объект customer успешно обновлен в БД: {} ", customerCheck);
        return serviceHelper.createDTOFromCustomer(customerCheck);
    }

    /**
     * Получение Customerиз базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полученного Customer
     */
    @Override
    public CustomerDTO getCustomer(int id) {
        Customer customer = customerRepository.getOne(id);
        log.debug("getCustomer() Объект customer успешно получен из БД: {}", customer);
        return serviceHelper.createDTOFromCustomer(customer);
    }

    /**
     * Получение всех Customer из базы
     *
     * @return - коллекция CustomerDTO конвертированная из полученного коллекции Customer
     */
    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDTOList.add(serviceHelper.createDTOFromCustomer(customer));
        }
        log.debug("getAllCustomer() Объекты customer успешно получены из БД");
        return customerDTOList;
    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     * @return - CustomerDTO конвертированный из удаленного Customer
     */
    @Override

    public CustomerDTO deleteById(int id) {
        CustomerDTO customerDTO = serviceHelper.createDTOFromCustomer(customerRepository.getOne(id));
        customerRepository.deleteById(id);
        log.debug("deleteById() Объект customer успешно удален из БД");
        return customerDTO;
    }
}