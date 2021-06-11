package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.helper.ServiceHelper;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с SupplierRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    private final ServiceHelper serviceHelper = new ServiceHelper();

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @return экземпляр supplierDTO
     */
    @Override
    public SupplierDTO createRandomSupplier() {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(serviceHelper.generateRandomWord());
        supplier.setPhone(serviceHelper.getRandomNumber());
        supplierRepository.saveAndFlush(supplier);
        Supplier supplierCheck = supplierRepository.getOne(supplier.getSupplierId());
        log.debug("createRandomSupplier() Объект product успешно записан в БД: {} ", supplierCheck);
        return serviceHelper.createDTOFromSupplier(supplierCheck);
    }

    /**
     * Создание и запись в БД екземпляра Supplier на основании объекта supplierDTO
     *
     * @param supplierDTO - Экземпляр supplierDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    @Override
    public SupplierDTO create(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(supplierDTO.getCompanyName());
        supplier.setPhone(supplierDTO.getPhone());
        supplierRepository.saveAndFlush(supplier);
        Supplier supplierCheck = supplierRepository.getOne(supplier.getSupplierId());
        log.debug("create() Объект product успешно записан в БД: {} ", supplierCheck);
        return serviceHelper.createDTOFromSupplier(supplierCheck);
    }

    /**
     * Обновление случайными данными и запись в БД екземпляра Supplier
     *
     * @param supplierDTO - Экземпляр supplierDTO
     * @return - Экземпляр supplierDTO
     */
    @Override
    public SupplierDTO updateRandomData(SupplierDTO supplierDTO) {
        supplierDTO.setCompanyName(supplierDTO.getCompanyName() + "+" + serviceHelper.generateRandomWord());
        supplierRepository.saveAndFlush(serviceHelper.createSupplierFromDTO(supplierDTO));
        Supplier supplierCheck = supplierRepository.getOne(supplierDTO.getSupplierId());
        log.debug("updateRandomData() Объект supplier успешно обновлен в БД: {} ", supplierCheck);
        return serviceHelper.createDTOFromSupplier(supplierCheck);
    }

    /**
     * Обновление и запись в БД экземпляра Supplier
     *
     * @param id          - id экземпляра supplier в базе, который необходимо изменить
     * @param supplierDTO - экземпляр supplierDTO, на который необходимо изменить
     * @return - результат операции supplierDTO конвертированный из Supllier полученного из базы
     */
    @Override
    public SupplierDTO update(int id, SupplierDTO supplierDTO) {
        Supplier updateSupplier = supplierRepository.getOne(id);
        updateSupplier.setCompanyName(supplierDTO.getCompanyName());
        updateSupplier.setPhone(supplierDTO.getPhone());
        supplierRepository.saveAndFlush(updateSupplier);
        Supplier supplierCheck = supplierRepository.getOne(updateSupplier.getSupplierId());
        log.info("updatesupplierWithId() Объект supplier успешно обновлен: {} ", supplierCheck);
        return serviceHelper.createDTOFromSupplier(supplierCheck);
    }

    /**
     * Получение Supplier из базы
     *
     * @param id - id Supplier, которое необходимло получить
     * @return - - SupplierDTO конвертированный из полученного Supplier
     */
    @Override
    public SupplierDTO getSupplier(int id) {
        Supplier supplier = supplierRepository.getOne(id);
        log.debug("getSupplier() Объект supplier успешно получен из БД");
        return serviceHelper.createDTOFromSupplier(supplier);
    }

    /**
     * Получение всех Supplier из базы
     *
     * @return - коллекция SupplierDTO конвертированная из полученого Supplier
     */
    @Override
    public List<SupplierDTO> getAllSupplier() {
        List<Supplier> customerList = supplierRepository.findAll();
        List<SupplierDTO> customerDTOList = new ArrayList<>();
        for (Supplier supplier : customerList) {
            customerDTOList.add(serviceHelper.createDTOFromSupplier(supplier));
        }
        log.debug("getAllSupplier() Объекты supplier успешно получены из БД");
        return customerDTOList;
    }

    /**
     * Удаление Supplier из базы по id
     *
     * @param id - id Supplier для удаления
     * @return - SupplierDTO конвертированный из удаленного Supplier
     */
    @Override
    public SupplierDTO deleteById(int id) {
        SupplierDTO supplierDTO = serviceHelper.createDTOFromSupplier(supplierRepository.getOne(id));
        supplierRepository.deleteById(id);
        log.debug("deleteById() Объект supplier успешно удален из БД");
        return supplierDTO;
    }

}