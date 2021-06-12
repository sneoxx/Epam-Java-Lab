package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.domain.entity.Supplier;

import java.util.List;

/**
 * Интерфейс для работы с SupplierServiceImpl
 */
public interface SupplierService {

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @return - supplier записанный в базу
     */
    Supplier createRandomSupplier();

    /**
     * Создание и запись в БД екземпляра Supplier на основании объекта supplierDTO
     *
     * @param supplier - Экземпляр supplier
     * @return - supplier записанный в базу
     */
    Supplier create(Supplier supplier);

    /**
     * Обновление случайными данными и запись в БД екземпляра Supplier
     *
     * @param supplier - Экземпляр supplier
     * @return - Экземпляр supplier
     */
    Supplier updateRandomData(Supplier supplier);

    /**
     * Обновление и запись в БД экземпляра Supplier
     *
     * @param id          - id экземпляра supplier в базе, который необходимо изменить
     * @param supplier - экземпляр supplier, на который необходимо изменить
     * @return - результат операции supplier конвертированный из Supllier полученного из базы
     */
    Supplier update(int id, Supplier supplier);

    /**
     * Получение Supplier из базы
     *
     * @param id - id Supplier, которое необходимло получить
     * @return - - Supplier
     */
    Supplier getSupplier(int id);

    /**
     * Получение всех Supplier из базы
     *
     * @return - коллекция list Supplier
     */
    List<Supplier> getAllSupplier();

    /**
     * Удаление Supplier из базы по id
     *
     * @param id - id Supplier для удаления
     * @return - удаленный Supplier
     */
    Supplier deleteById(int id);

}