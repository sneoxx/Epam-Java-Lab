package com.zaraev.epam.javacourses.repository.stub;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.helper.RepositoryHelper;
import com.zaraev.epam.javacourses.repository.ICustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;


@Profile("local")
@Component
@Slf4j
public class CustomerRepositoryStub implements ICustomerRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;// = Persistence.createEntityManagerFactory("WER");

    @Autowired
    private RepositoryHelper repositoryHelper;

    /**
     * Запись в БД екземпляра Customer - Ничего не сделает
     *
     * @return - вернет застабленный экземпляр Customer
     */
    @Override
    public Customer create(Customer customer) {
        var stub = repositoryHelper.customer();
        log.info("create() Объект customer застаблен {}", stub);
        return stub;
    }

    /**
     * Изменение в БД экземпляра customer - Ничего не сделает
     *
     * @param customer - экземпляр customer, который необходимо изменить
     */
    @Override
    public void update(Customer customer) {
        log.info("update() Объект customer застаблен ");
        return;
    }

    /**
     * Получение из БД объекта Customer - Ничего не сделает
     *
     * @param id - id объекта Customer который необходимо получить
     * @return - вернет застабленный экземпляр Customer
     */
    @Override
    public Customer getCustomer(int id) {
        var stub = repositoryHelper.customer();
        ;
        log.info("getCustomer() Объект customer застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД объекта Customer по экземпляру Customer - Ничего не сделает
     *
     * @param customerName - id объекта Customer который необходимо получить
     * @return - вернет застабленный экземпляр Customer
     */
    @Override
    public Customer getCustomerWithInstance(String customerName) {
        var stub = repositoryHelper.customer();
        log.info("getCustomerWithInstance() Объект customer застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД всех объектов Customer - Ничего не сделает
     *
     * @return - - Коллекция List из застабленного Customer
     */
    @Override
    public List<Customer> getAllCustomer() {
        var stub = repositoryHelper.customer();
        Customer[] customers = {stub};
        log.info("getAllCustomer() Объект customer застаблен", Arrays.asList(customers));
        return Arrays.asList(customers);
    }

    /**
     * Удаление объекта customer из БД - Ничего не сделает
     *
     * @param customer - удаляемый объект
     */
    @Override
    public void deleteCustomer(Customer customer) {
        log.info("deleteCustomer() Объект customer застаблен");
        return;
    }

    /**
     * Удаление объекта customer из БД по id - Ничего не сделает
     *
     * @param id - id удаляемого customer
     */
    @Override
    public void deleteCustomerWithId(int id) {
        log.info("deleteCustomerWithId() Объект customer застаблен");
        return;
    }
}