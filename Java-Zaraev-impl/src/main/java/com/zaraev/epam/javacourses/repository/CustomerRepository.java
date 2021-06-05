package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.repository.impl.ICustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Profile("!local")
@Component
@Slf4j
public class CustomerRepository implements ICustomerRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private Environment environment;

    /**
     * Запись в БД екземпляра Customer
     *
     * @return вернет занесенный экземпляр Customer
     */
    @Override
    public Customer create(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(customer);
            transaction.commit();
            entityManager.close();
            log.info("createCustomer() Объект Customer создан и занесен в БД: {}", customer);
            return customer;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра customer
     *
     * @param customer - экземпляр customer, который необходимо изменить
     */

    @Override
    public void update(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            log.debug("updateCustomer() Объект сustomer передан на обновление: {} ", customer);
            transaction.begin();
            entityManager.merge(customer);
            transaction.commit();
            entityManager.close();
            log.info("updateCustomer() Объект сustomer успешно обновлен: {} ", customer);
        } catch (Exception e) {
            log.error("updateCustomer() Ошибка обновления объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Получение из БД объекта Customer
     *
     * @param id - id объекта Customer который необходимо получить
     * @return - объект Customer из БД
     */
    @Override
    public Customer getCustomer(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try {
            customer = entityManager.find(Customer.class, id);
            log.info("getCustomer() Объект customer успешно получен из БД {}", customer);
            entityManager.close();
            return customer;
        } catch (Exception e) {
            log.error("getCustomer() Ошибка получения из БД объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
        return customer;
    }

    /**
     * Получение из БД объекта Customer по экземпляру Customer
     *
     * @param customerName - id объекта Customer который необходимо получить
     * @return - объект Customer из БД
     */
    @Override
    public Customer getCustomerWithInstance(String customerName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try {
            TypedQuery<Customer> query = entityManager.createQuery(
                    "SELECT u FROM Customer u WHERE u.customerName = :customerName", Customer.class);
            customer = query.setParameter("customerName", customerName)
                    .getSingleResult();
            log.info("getCustomer() Объект customer успешно получен из БД {}", customer);
            entityManager.close();
            return customer;
        } catch (Exception e) {
            log.error("getCustomer() Ошибка получения из БД объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
        return customer;
    }

    /**
     * Получение из БД всех объектов Customer
     *
     * @return - Коллекция List всех объектов Customer из БД
     */
    @Override
    public List<Customer> getAllCustomer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customerList = new ArrayList<>();
        try {
            String query = "SELECT c FROM Customer c";
            customerList = entityManager
                    .createQuery(query, Customer.class)
                    .getResultList();
            entityManager.close();
            log.info("getAllCustomer() Выведен список всех Customer: {}", customerList);
            return customerList;
        } catch (Exception e) {
            log.error("getAllCustomer() Ошибка получения из БД объектов сustomer: ", e);
        } finally {
            entityManager.close();
        }
        return customerList;
    }

    /**
     * Удаление объекта customer из БД
     *
     * @param customer - удаляемый объект
     */
    @Override
    public void deleteCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerId());
            transaction.begin();
            log.debug("deleteCustomer() Объект customer передан на удаление: {}", tempCustomer);
            if (entityManager.contains(tempCustomer)) {
                entityManager.remove(tempCustomer);
                transaction.commit();
                log.info("deleteCustomer() Объект customer успешно удален: {}", tempCustomer);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteCustomer() Ошибка удаления объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Удаление объекта customer из БД по id
     *
     * @param id - id удаляемого customer
     */
    @Override
    public void deleteCustomerWithId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Customer tempCustomer = entityManager.find(Customer.class, id);
            transaction.begin();
            log.debug("deleteCustomer() Объект customer передан на удаление: {}", tempCustomer);
            if (entityManager.contains(tempCustomer)) {
                entityManager.remove(tempCustomer);
                transaction.commit();
                log.info("deleteCustomer() Объект customer успешно удален: {}", tempCustomer);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteCustomer() Ошибка удаления объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
    }
}