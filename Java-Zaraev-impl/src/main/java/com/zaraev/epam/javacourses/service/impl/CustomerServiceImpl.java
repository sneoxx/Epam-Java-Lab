package com.zaraev.epam.javacourses.service.impl;


import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * Сервис для работы с CustomerRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Создание и запись в БД рандомного Customer
     *
     * @return - сustomer записанный в базу
     */
    @Override
    public Customer createRandomCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(generateRandomWord());
        customer.setPhone(getRandomNumber());
        Customer customerCheck = customerRepository.save(customer);
        log.debug("createRandomCustomer() Объект customer успешно записан в БД: {} ", customerCheck);
        return customerCheck;
    }

    /**
     * Создание и запись в БД екземпляра customer
     *
     * @param customer - Экземпляр customer
     * @return - сustomer записанный в базу
     */
    @Override
    public Customer create(Customer customer) {
       Customer customerCheck = customerRepository.save(customer);
        log.debug("create() Объект customer успешно записан в БД: {} ", customerCheck);
        return customerCheck;
    }

    /**
     * Обновление случайными данными и запись в БД екземпляра Customer
     *
     * @param customer - экземпляр customer, на который необходимо изменить
     * @return - сustomer обновленный в базе
     */
    @Override
    public Customer updateRandomData(Customer customer) {
        customer.setCustomerName(customer.getCustomerName() + "+" + generateRandomWord());
        Customer customerCheck = customerRepository.save(customer);
        log.debug("updateRandomData() Объект customer успешно обновлен в БД: {} ", customer);
        return customerCheck;
    }

    /**
     * Обновление и запись в БД экземпляра customer
     *
     * @param id      - id экземпляра customer в базе, который необходимо изменить
     * @param customer - экземпляр customer, на который необходимо изменить
     * @return - сustomer обновленный в базе
     */
    @Override
    public Customer update(int id, Customer customer) {
        Customer updateCustomer = customerRepository.getOne(id);
        updateCustomer.setCustomerName(customer.getCustomerName());
        updateCustomer.setPhone(customer.getPhone());
        Customer customerCheck  = customerRepository.save(updateCustomer);
        log.debug("updateRandomData() Объект customer успешно обновлен в БД: {} ", customerCheck );
        return customerCheck;
    }

    /**
     * Получение Customer из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - сustomer полученный из базы или новый сustomer в случае отстутствия такового id в БД
     */
    @Override
    public Customer getCustomer(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        log.debug("getCustomer() Объект customer успешно получен из БД: {}", customer );
        return customer;
//        Optional<Customer> optionalCustomer = customerRepository.findById(id);
//        if(optionalCustomer.isPresent()) {
//            Customer customer = optionalCustomer.get();
//            log.debug("getCustomer() Объект customer успешно получен из БД: {}", customer );
//            return customer;
//        }
//        log.debug("getProduct() Объект customer не найден, создан новый Customer");
//        return new Customer();
    }

    /**
     * Получение всех Customer из базы
     *
     * @return - коллекция list Customer
     */
    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        log.debug("getAllCustomer() Объекты customer успешно получены из БД: {}", customerList);
        return customerList;
    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     * @return - удаленный Customer
     */
    @Override
    public Customer deleteById(int id) {
        Customer customer = customerRepository.getOne(id);
        customerRepository.deleteById(id);
        log.debug("deleteById() Объект customer успешно удален из БД: {}", customer);
        return customer;
    }

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @return - случайное число
     */
    public String getRandomNumber() {
        return Integer.toString(1 + (int) (Math.random() * 10000));
    }

    /**
     * Генерация случайного слова
     *
     * @return - случайное слово
     */
    public String generateRandomWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(2) + 3];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }
}