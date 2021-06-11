package com.zaraev.epam.javacourses.service;

import com.zaraev.epam.javacourses.dto.SupplierDTO;

import java.util.List;

/**
 * Интерфейс для работы с SupplierServiceImpl
 */
public interface SupplierService {

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @return экземпляр supplierDTO
     */
    SupplierDTO createRandomSupplier();

    /**
     * Создание и запись в БД екземпляра Supplier на основании объекта supplierDTO
     *
     * @param supplierDTO - Экземпляр supplierDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    SupplierDTO create(SupplierDTO supplierDTO);

    /**
     * Обновление случайными данными и запись в БД екземпляра Supplier
     *
     * @param supplierDTO - Экземпляр supplierDTO
     * @return - Экземпляр supplierDTO
     */
    SupplierDTO updateRandomData(SupplierDTO supplierDTO);

    /**
     * Обновление и запись в БД экземпляра Supplier
     *
     * @param id          - id экземпляра supplier в базе, который необходимо изменить
     * @param supplierDTO - экземпляр supplierDTO, на который необходимо изменить
     * @return - результат операции supplierDTO конвертированный из Supllier полученного из базы
     */
    SupplierDTO update(int id, SupplierDTO supplierDTO);

    /**
     * Получение Supplier из базы
     *
     * @param id - id Supplier, которое необходимло получить
     * @return - - SupplierDTO конвертированный из полученного Supplier
     */
    SupplierDTO getSupplier(int id);

    /**
     * Получение всех Supplier из базы
     *
     * @return - коллекция SupplierDTO конвертированная из полученого Supplier
     */
    List<SupplierDTO> getAllSupplier();

    /**
     * Удаление Supplier из базы по id
     *
     * @param id - id Supplier для удаления
     * @return - SupplierDTO конвертированный из удаленного Supplier
     */
    SupplierDTO deleteById(int id);

}