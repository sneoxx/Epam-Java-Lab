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
     * @param supplier - экземпляр supplierDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    Product createRandomProduct(Supplier supplier);

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @param product - Экземпляр productDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    Product create(Product product);

    /**
     * Обновление случайными данными и запись в БД екземпляра Product
     *
     * @param product - экземпляр productDTO, на который необходимо изменить
     * @return - результат операции productDTO конвертированный из Product полученного из базы
     */
    Product updateRandomData(Product product);

    /**
     * Обновление и запись в БД екземпляра product
     *
     * @param id         - id экземпляра product в базе, который необходимо изменить
     * @param product - экземпляр productDTO, на который необходимо изменить
     * @return - ProductDTO конвертированный из обновленного Product
     */
    Product update(int id, Product product);

    /**
     * Получение Product из базы
     *
     * @param id - id Product, которое необходимло получить
     * @return - ProductDTO созданный из полченного Customer
     */
    Product getProduct(int id);

    /**
     * Получение всех Product из базы
     *
     * @return - коллекция ProductDTO конвертированная из полученной коллекции Product
     */
    List<Product> getAllProduct();

    /**
     * Удаление Product из базы по id
     *
     * @param id - id Product для удаления
     * @return - ProductDTO конвертированный из удаленного Product
     */
    Product deleteById(int id);

}