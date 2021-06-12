package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Сервис для работы с SupplierRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @return экземпляр supplierDTO
     */
    @Override
    public Supplier createRandomSupplier() {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(generateRandomWord());
        supplier.setPhone(getRandomNumber());
        Supplier supplierCheck = supplierRepository.save(supplier);
        log.debug("createRandomSupplier() Объект product успешно записан в БД: {} ", supplierCheck);
        return supplierCheck;
    }

    /**
     * Создание и запись в БД екземпляра Supplier на основании объекта supplierDTO
     *
     * @param supplier - Экземпляр supplierDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    @Override
    public Supplier create(Supplier supplier) {
        Supplier supplierCheck = supplierRepository.save(supplier);
        log.debug("create() Объект product успешно записан в БД: {} ", supplierCheck);
        return supplierCheck;
    }

    /**
     * Обновление случайными данными и запись в БД екземпляра Supplier
     *
     * @param supplier - Экземпляр supplierDTO
     * @return - Экземпляр supplierDTO
     */
    @Override
    public Supplier updateRandomData(Supplier supplier) {
        supplier.setCompanyName(supplier.getCompanyName() + "+" + generateRandomWord());
        Supplier supplierCheck = supplierRepository.save(supplier);
        log.debug("updateRandomData() Объект supplier успешно обновлен в БД: {} ", supplierCheck);
        return supplierCheck;
    }

    /**
     * Обновление и запись в БД экземпляра Supplier
     *
     * @param id          - id экземпляра supplier в базе, который необходимо изменить
     * @param supplier - экземпляр supplierDTO, на который необходимо изменить
     * @return - результат операции supplierDTO конвертированный из Supllier полученного из базы
     */
    @Override
    public Supplier update(int id, Supplier supplier) {
        Supplier updateSupplier = supplierRepository.getOne(id);
        updateSupplier.setCompanyName(supplier.getCompanyName());
        updateSupplier.setPhone(supplier.getPhone());
        Supplier supplierCheck = supplierRepository.save(updateSupplier);
        log.info("updatesupplierWithId() Объект supplier успешно обновлен: {} ", supplierCheck);
        return supplierCheck;
    }

    /**
     * Получение Supplier из базы
     *
     * @param id - id Supplier, которое необходимло получить
     * @return - - SupplierDTO конвертированный из полученного Supplier
     */
    @Override
    public Supplier getSupplier(int id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();
            log.debug("getSupplier() Объект supplier успешно получен из БД: {}", supplier);
            return optionalSupplier.get();
            }
        log.debug("getProduct() Объект supplier не найден, создан новый supplier");
        return new Supplier();
    }


    /**
     * Получение всех Supplier из базы
     *
     * @return - коллекция SupplierDTO конвертированная из полученого Supplier
     */
    @Override
    public List<Supplier> getAllSupplier() {
        List<Supplier> supplierList = supplierRepository.findAll();
        log.debug("getAllSupplier() Объекты supplier успешно получены из БД: {}",  supplierList);
        return supplierList;
    }

    /**
     * Удаление Supplier из базы по id
     *
     * @param id - id Supplier для удаления
     * @return - SupplierDTO конвертированный из удаленного Supplier
     */
    @Override
    public Supplier deleteById(int id) {
        Supplier supplier = supplierRepository.getOne(id);
        supplierRepository.deleteById(id);
        log.debug("deleteById() Объект supplier успешно удален из БД: {}", supplier);
        return supplier;
    }

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @return - случайное число
     */
    public String getRandomNumber() {
        return Integer.toString(1 + (int) (Math.random() * 10000));
    }


    /**
     * Генерация случайного слова
     *
     * @return - случайное слово
     */
    public String generateRandomWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(2) + 3];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

}