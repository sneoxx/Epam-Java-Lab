package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;

import java.util.List;

/**
 * Интерфейс для работы с ProductServiceImpl
 */
public interface ProductService {

    /**
     * Создание случайного product и запись в БД
     *
     * @param supplier - экземпляр supplier
     * @return - product записанный в базу
     */
    Product createRandomProduct(Supplier supplier);

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @param product - Экземпляр product
     * @return - product записанный в базу
     */
    Product create(Product product);

    /**
     * Обновление случайными данными и запись в БД екземпляра Product
     *
     * @param product - экземпляр product, на который необходимо изменить
     * @return - product обновленный в базе
     */
    Product updateRandomData(Product product);

    /**
     * Обновление и запись в БД екземпляра product
     *
     * @param id         - id экземпляра product в базе, который необходимо изменить
     * @param product - экземпляр product, на который необходимо изменить
     * @return - product обновленный в базе
     */
    Product update(int id, Product product);

    /**
     * Получение Product из базы
     *
     * @param id - id Product, которое необходимло получить
     * @return - Product полученный из базы или новый Product в случае отстутствия такового id в БД
     */
    Product getProduct(int id);

    /**
     * Получение всех Product из базы
     *
     * @return - коллекция list Product
     */
    List<Product> getAllProduct();

    /**
     * Удаление Product из базы по id
     *
     * @param id - id Product для удаления
     * @return - удаленный Product
     */
    Product deleteById(int id);

}