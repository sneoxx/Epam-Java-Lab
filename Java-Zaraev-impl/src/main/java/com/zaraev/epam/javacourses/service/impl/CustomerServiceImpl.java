package com.zaraev.epam.javacourses.service.impl;


import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.helper.ServiceHelper;
import com.zaraev.epam.javacourses.repository.impl.ICustomerRepository;
import com.zaraev.epam.javacourses.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ServiceHelper serviceHelper;

    /**
     * Создание случайного сustomer и передача на запись в БД
     *
     * @return - экземпляр customer
     */
    @Override
    public Customer createRandomCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(serviceHelper.generateRandomWord());
        customer.setPhone(serviceHelper.getRandomNumber());
        customerRepository.create(customer);
        return customer;
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
        Customer customerCheck = customerRepository.getCustomerWithInstance(customer.getCustomerName());
        return createCustomerDTO(customerCheck);
    }

    /**
     * Обновление екземпляра customer и передача на обновление в БД
     *
     * @param customer - экземпляр customer, на который необходимо изменить
     * @return - результат опрерации сustomerDTO
     */
    @Override
    public CustomerDTO update(Customer customer) {
        customer.setCustomerName(customer.getCustomerName() + "+" + serviceHelper.generateRandomWord());
        customerRepository.update(customer);
        Customer customerCheck = customerRepository.getCustomerWithInstance(customer.getCustomerName());
        return createCustomerDTO(customerCheck);
    }

    /**
     * Обновление экземпляра customer и передача на обновление в БД
     *
     * @param id          - id экземпляра customer в базе, который необходимо изменить
     * @param customerDTO - экземпляр customer, на который необходимо изменить
     * @return - результат опрерации сustomerDTO
     */
    @Override
    public CustomerDTO updateCustomerWithId(int id, CustomerDTO customerDTO) {
        Customer updatecustomer = customerRepository.getCustomer(id);
        log.debug("updateProductWithId() Объект customerDTO передан на обновление: {} ", customerDTO);
        updatecustomer.setCustomerName(customerDTO.getCustomerName());
        updatecustomer.setPhone(customerDTO.getPhone());
        log.info("updateProductWithId() Объект customer успешно обновлен: {} ", updatecustomer);
        customerRepository.update(updatecustomer);
        Customer customerCheck = customerRepository.getCustomerWithInstance(updatecustomer.getCustomerName());
        return createCustomerDTO(customerCheck);
    }

    /**
     * Получение CustomerDTO из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полченного Customer
     */
    @Override
    public CustomerDTO getCustomer(int id) {
        Customer customer = customerRepository.getCustomer(id);
        return createCustomerDTO(customer);
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
            customerDTOList.add(createCustomerDTO(customer));
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
        customerRepository.deleteCustomerWithId(id);
    }

    /**
     * Создание сustomerDTO из customer
     *
     * @param customer - исходный supplier
     * @return - полученный сustomerDTO
     */
    @Override
    public CustomerDTO createCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setCustomerId(customer.getCustomerId());
        return customerDTO;
    }
}