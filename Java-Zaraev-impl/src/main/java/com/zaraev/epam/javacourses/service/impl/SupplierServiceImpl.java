package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
     * @return - supplier записанный в базу
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
     * Создание и запись в БД екземпляра Supplier
     *
     * @param supplier - Экземпляр supplier для записи
     * @return - supplier записанный в базу
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
     * @param supplier - Экземпляр supplier
     * @return - supplier обновленый в базе
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
     * @param supplier - экземпляр supplier, на который необходимо изменить
     * @return - supplier обновленый в базе
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
     * @return - supplier полученный из базы или новый supplier в случае отстутствия такового id в БД
     */
    @Override
    public Supplier getSupplier(int id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(RuntimeException::new);
        log.debug("getSupplier() Объект supplier успешно получен из БД: {}", supplier);
        return supplier;
//        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
//        if(optionalSupplier.isPresent()) {
//            Supplier supplier = optionalSupplier.get();
//            log.debug("getSupplier() Объект supplier успешно получен из БД: {}", supplier);
//            return optionalSupplier.get();
//            }
//        log.debug("getProduct() Объект supplier не найден, создан новый supplier");
//        return new Supplier();
//    }
    }

    /**
     * Получение всех Supplier из базы
     *
     * @return - коллекция list Supplier
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
     * @return - удаленный Supplier
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