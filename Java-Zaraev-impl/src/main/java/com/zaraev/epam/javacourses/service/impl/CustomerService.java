package com.zaraev.epam.javacourses.service.impl;


import com.zaraev.epam.javacourses.bufferdata.BufferDataCustomer;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class CustomerService {

    public CustomerRepository customerRepository = new CustomerRepository();

    /**
     * Создание случайного сustomer и передача на запись в БД
     *
     * @return - экземпляр customer
     */
    public Customer createRandomCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(generateRandomWord());
        customer.setPhone(getRandomNumber());
        customerRepository.create(customer);
        return customer;
    }

    /**
     * Создание и передача на запись в БД екземпляра customer на основании объекта BufferDatacustomer c проверкой наличия в базе
     *
     * @param bufferDataCustomer - Экземпляр BufferDatacustomer
     * @return - экземпляр customer
     */
    public Customer create(BufferDataCustomer bufferDataCustomer) {
        Customer customer = new Customer();
        customer.setCustomerName(bufferDataCustomer.getCustomerName());
        customer.setPhone(bufferDataCustomer.getPhone());
        customerRepository.create(customer);
        return customer;
    }

    /**
     * Обновление екземпляра customer и передача на обновление в БД
     *
     * @param customer - экземпляр customer, на который необходимо изменить
     */
    public void update(Customer customer) {
        customer.setCustomerName(customer.getCustomerName() + "+" + generateRandomWord());
        customerRepository.update(customer);
    }

    /**
     * Обновление экземпляра customer и передача на обновление в БД
     *
     * @param id       - id экземпляра customer в базе, который необходимо изменить
     * @param customer - экземпляр customer, на который необходимо изменить
     */
    public void updateCustomerWithId(int id, Customer customer) {
        Customer updatecustomer = customerRepository.getCustomer(id);
        log.debug("updateProductWithId() Объект customer передан на обновление: {} ", customer);
        updatecustomer.setCustomerName(customer.getCustomerName());
        updatecustomer.setPhone(customer.getPhone());
        log.info("updateProductWithId() Объект customer успешно обновлен: {} ", customer);
        customerRepository.update(updatecustomer);
    }

    /**
     * Генерация случайного слова
     *
     * @return - случайное слово
     */
    public static String generateRandomWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(2) + 3];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @return - случайное число
     */
    public String getRandomNumber() {
        return Integer.toString(1 + (int) (Math.random() * 10000));
    }
}