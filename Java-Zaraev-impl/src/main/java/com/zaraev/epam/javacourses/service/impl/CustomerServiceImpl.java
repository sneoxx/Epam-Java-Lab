package com.zaraev.epam.javacourses.service.impl;


import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.helper.ServiceHelper;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с CustomerRepository
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final ServiceHelper serviceHelper = new ServiceHelper();

    /**
     * Создание случайного сustomer и передача на запись в БД
     *
     * @return - экземпляр customer
     */
    @Override
    public CustomerDTO createRandomCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(serviceHelper.generateRandomWord());
        customer.setPhone(serviceHelper.getRandomNumber());
        Customer customer1 = customerRepository.create(customer);
        return serviceHelper.createDTOFromCustomer(customer1);
    }

    /**
     * Создание и передача на запись в БД екземпляра customer
     *
     * @param customerDTO - Экземпляр customerDTO
     * @return - результат опрерации сustomerDTO полученный из базы
     */
    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPhone(customerDTO.getPhone());
        customerRepository.create(customer);
        Customer customerCheck = customerRepository.get(customer.getCustomerId());
        return serviceHelper.createDTOFromCustomer(customerCheck);
    }

    /**
     * Обновление екземпляра customer и передача на обновление в БД
     *
     * @param customerDTO - экземпляр customer, на который необходимо изменить
     * @return - результат опрерации сustomerDTO
     */
    @Override
    public CustomerDTO updateRandomData(CustomerDTO customerDTO) {
        customerDTO.setCustomerName(customerDTO.getCustomerName() + "+" + serviceHelper.generateRandomWord());
        customerRepository.update(serviceHelper.createCustomerFromDTO(customerDTO));
        Customer customerCheck = customerRepository.get(customerDTO.getCustomerId());
        return serviceHelper.createDTOFromCustomer(customerCheck);
    }

    /**
     * Обновление экземпляра customer и передача на обновление в БД
     *
     * @param id          - id экземпляра customer в базе, который необходимо изменить
     * @param customerDTO - экземпляр customer, на который необходимо изменить
     * @return - результат опрерации сustomerDTO
     */
    @Override
    public CustomerDTO update(int id, CustomerDTO customerDTO) {
        Customer updateCustomer = customerRepository.get(id);
        log.debug("updateProductWithId() Объект customerDTO передан на обновление: {} ", customerDTO);
        updateCustomer.setCustomerName(customerDTO.getCustomerName());
        updateCustomer.setPhone(customerDTO.getPhone());
        log.info("updateProductWithId() Объект customer успешно обновлен: {} ", updateCustomer);
        customerRepository.update(updateCustomer);
        Customer customerCheck = customerRepository.get(updateCustomer.getCustomerId());
        return serviceHelper.createDTOFromCustomer(customerCheck);
    }

    /**
     * Получение CustomerDTO из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полченного Customer
     */
    @Override
    public CustomerDTO getCustomer(int id) {
        Customer customer = customerRepository.get(id);
        return serviceHelper.createDTOFromCustomer(customer);
    }

    /**
     * Получение всех CustomerDTO из базы
     *
     * @return - CustomerDTO созданный из полученного Customer
     */
    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customerList = customerRepository.getAllCustomer();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDTOList.add(serviceHelper.createDTOFromCustomer(customer));
        }
        return customerDTOList;
    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     */
    @Override
    public void deleteCustomerWithId(int id) {
        customerRepository.delete(id);
    }

}