package com.zaraev.epam.javacourses.repository.stub;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * Класс для возврата застабленных значения класса Customer без обращения к самой БД
 */
@Profile("local")
@Repository
@RequiredArgsConstructor
@Data
@Slf4j
public class CustomerRepositoryStubImpl implements CustomerRepository {

    private final MessageSource messageSource;

    @Value("${locale:en}")
    private Locale locale;

    /**
     * Запись в БД екземпляра Customer - Ничего не сделает
     *
     * @return - вернет застабленный экземпляр Customer
     */
    @Override
    public Customer create(Customer customer) {
        var stub = getStubCustomer();
        log.info("create() Объект customer застаблен {}", stub);
        return stub;
    }

    /**
     * Изменение в БД экземпляра customer - Ничего не сделает
     *
     * @param customer - экземпляр customer, который необходимо изменить
     */
    @Override
    public Customer update(Customer customer) {
        var stub = getStubCustomer();
        log.info("update() Объект customer застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД объекта Customer - Ничего не сделает
     *
     * @param id - id объекта Customer который необходимо получить
     * @return - вернет застабленный экземпляр Customer
     */
    @Override
    public Customer get(int id) {
        var stub = getStubCustomer();
        log.info("getCustomer() Объект customer застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД всех объектов Customer - Ничего не сделает
     *
     * @return - - Коллекция List из застабленного Customer
     */
    @Override
    public List<Customer> getAllCustomer() {
        var stub = getStubCustomer();
        Customer[] customers = {stub};
        log.info("getAllCustomer() Объект customer застаблен", Arrays.asList(customers));
        return Arrays.asList(customers);
    }


    /**
     * Удаление объекта customer из БД по id - Ничего не сделает
     *
     * @param id - id удаляемого customer
     */
    @Override
    public void delete(int id) {
        log.info("deleteCustomerWithId() Объект customer застаблен");
    }

    private Customer getStubCustomer() {
        var customer = new Customer();
        customer.setCustomerId(Integer.parseInt(messageSource.getMessage("customerId", null, "1", locale)));
        customer.setCustomerName(messageSource.getMessage("customerName", null, "Error", locale));
        customer.setPhone(messageSource.getMessage("customerPhone", null, "Error", locale));
        return customer;
    }

}