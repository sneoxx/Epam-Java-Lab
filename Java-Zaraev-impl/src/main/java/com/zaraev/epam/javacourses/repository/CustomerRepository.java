package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Random;

@Slf4j
public class CustomerRepository {

        public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WER");
        static int orderNumber;

        /**
         * Создание и занесение в БД екземпляра Customer
         *
         * @return вернет занесенный экземпляр Customer
         */
        public Customer createCustomer() {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                Customer customer = new Customer();
                customer.setCustomerName(generateRandomWord());
                customer.setPhone(getRandomNumber());
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
         * Создание и занесение в БД екземпляра Customer на основании объекта customer
         *
         * @param customer - объект customer
         * @return вернет занесенный экземпляр Customer
         */
        public Customer createCustomerWithInstance(Customer customer) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                customer.setCustomerId(null);
                transaction.begin();
                entityManager.persist(customer);
                transaction.commit();
                entityManager.close();
                log.info("createCustomerWithInstance() Объект Customer создан и занесен в БД: {}", customer);
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
        public void updateCustomer(Customer customer) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                log.debug("updateCustomer() Объект сustomer передан на обновление: {} ", customer);
                customer.setCustomerName(customer.getCustomerName() + "+" + generateRandomWord());
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
         * Изменение в БД экземпляра customer
         *
         * @param id       - id экземпляра customer в базе, который необходимо изменить
         * @param customer - экземпляр customer, который необходимо изменить
         */
        public void updateCustomerWithId(int id, Customer customer) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                Customer updateCustomer = entityManager.find(Customer.class, id);
                log.debug("updateCustomerWithId() Объект customer передан на обновление: {} ", customer);
                updateCustomer.setCustomerName(customer.getCustomerName());
                updateCustomer.setPhone(customer.getPhone());
                transaction.begin();
                entityManager.merge(updateCustomer);
                transaction.commit();
                log.info("updateCustomerWithId() Объект customer успешно обновлен: {} ", customer);
                entityManager.close();
            } catch (Exception e) {
                log.error("updateCustomerWithId() Ошибка обновления объекта сustomer: ", e);
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
         * Удаление объекта customer из БД
         *
         * @param customer - удаляемый объект
         */
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