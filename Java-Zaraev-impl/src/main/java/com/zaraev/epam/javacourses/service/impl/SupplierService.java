package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.bufferdata.BufferDataSupplier;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class SupplierService {

    SupplierRepository supplierRepository = new SupplierRepository();

    /**
     * Создание случайного supplier и передача на запись в БД
     *
     * @return экземпляр supplier
     */
    public Supplier createRandomSupplier() {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(supplier.getCompanyName() + "+" + generateRandomWord());
        supplierRepository.create(supplier);
        return supplier;
    }

    /**
     * Создание екземпляра supplier и передача на запись в БД на основании объекта BufferDatasupplier
     *
     * @param bufferDataSupplier - Экземпляр BufferDatasupplier
     * @return - экземпляр supplier
     */
    public Supplier create(BufferDataSupplier bufferDataSupplier) {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(bufferDataSupplier.getCompanyName());
        supplier.setPhone(bufferDataSupplier.getPhone());
        supplierRepository.create(supplier);
        return supplier;
    }

    /**
     * Обновление екземпляра supplier и передача на запись в БД
     *
     * @param supplier Экземпляр supplier
     */
    public void update(Supplier supplier) {
        supplier.setCompanyName(supplier.getCompanyName() + "+" + generateRandomWord());
        supplierRepository.update(supplier);
    }

    /**
     * Обновление екземпляра supplier и передача на запись в БД
     *
     * @param id       - id экземпляра supplier в базе, который необходимо изменить
     * @param supplier - экземпляр supplier, который необходимо изменить
     */
    public void updateSupplierWithId(int id, Supplier supplier) {
        Supplier updateSupplier = supplierRepository.getSupplier(id);
        log.debug("updatesupplierWithId() Объект supplier передан на обновление: {} ", supplier);
        updateSupplier.setCompanyName(supplier.getCompanyName());
        updateSupplier.setPhone(supplier.getPhone());
        log.info("updatesupplierWithId() Объект supplier успешно обновлен: {} ", supplier);
        supplierRepository.update(updateSupplier);
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
}