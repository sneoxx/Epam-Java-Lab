package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.dto.SupplierDTO;

import java.util.List;

/**
 * Интерфейс для работы с ProductServiceImpl
 */
public interface ProductService {

    /**
     * Создание случайного product и запись в БД
     *
     * @param supplierDTO - экземпляр supplierDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    ProductDTO createRandomProduct(SupplierDTO supplierDTO);

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @param productDTO - Экземпляр productDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    ProductDTO create(ProductDTO productDTO);

    /**
     * Обновление случайными данными и запись в БД екземпляра Product
     *
     * @param productDTO - экземпляр productDTO, на который необходимо изменить
     * @return - результат операции productDTO конвертированный из Product полученного из базы
     */
    ProductDTO updateRandomData(ProductDTO productDTO);

    /**
     * Обновление и запись в БД екземпляра product
     *
     * @param id         - id экземпляра product в базе, который необходимо изменить
     * @param productDTO - экземпляр productDTO, на который необходимо изменить
     * @return - ProductDTO конвертированный из обновленного Product
     */
    ProductDTO update(int id, ProductDTO productDTO);

    /**
     * Получение Product из базы
     *
     * @param id - id Product, которое необходимло получить
     * @return - ProductDTO созданный из полченного Customer
     */
    ProductDTO getProduct(int id);

    /**
     * Получение всех Product из базы
     *
     * @return - коллекция ProductDTO конвертированная из полученной коллекции Product
     */
    List<ProductDTO> getAllProduct();

    /**
     * Удаление Product из базы по id
     *
     * @param id - id Product для удаления
     * @return - ProductDTO конвертированный из удаленного Product
     */
    ProductDTO deleteById(int id);

}