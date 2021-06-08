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
     * Создание случайного supplier и передача на запись в БД
     *
     * @return экземпляр supplier
     */
    @Override
    public SupplierDTO createRandomSupplier() {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(serviceHelper.generateRandomWord());
        supplier.setPhone(serviceHelper.getRandomNumber());
        return serviceHelper.createDTOFromSupplier(supplierRepository.saveAndFlush(supplier));
    }

//    @Override
//    public SupplierDTO createRandomSupplier() {
//        Supplier supplier = new Supplier();
//        supplier.setCompanyName(serviceHelper.generateRandomWord());
//        supplier.setPhone(serviceHelper.getRandomNumber());
//        return serviceHelper.createDTOFromSupplier(supplierRepository.create(supplier));
//    }

    /**
     * Создание екземпляра supplier и передача на запись в БД на основании объекта supplierDTO
     *
     * @param supplierDTO - Экземпляр supplierDTO
     * @return - экземпляр supplierDTO
     */
    @Override
    public SupplierDTO create(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(supplierDTO.getCompanyName());
        supplier.setPhone(supplierDTO.getPhone());
        supplierRepository.saveAndFlush(supplier);
        Supplier supplierCheck = supplierRepository.getOne(supplier.getSupplierId());
        return serviceHelper.createDTOFromSupplier(supplierCheck);
    }


//    @Override
//    public SupplierDTO create(SupplierDTO supplierDTO) {
//        Supplier supplier = new Supplier();
//        supplier.setCompanyName(supplierDTO.getCompanyName());
//        supplier.setPhone(supplierDTO.getPhone());
//        supplierRepository.create(supplier);
//        Supplier supplierCheck = supplierRepository.get(supplier.getSupplierId());
//        return serviceHelper.createDTOFromSupplier(supplierCheck);
//    }

    /**
     * Обновление екземпляра supplier и передача на запись в БД
     *
     * @param supplierDTO - Экземпляр supplier
     * @return - Экземпляр supplierDTO
     */
    @Override
    public SupplierDTO updateRandomData(SupplierDTO supplierDTO) {
        supplierDTO.setCompanyName(supplierDTO.getCompanyName() + "+" + serviceHelper.generateRandomWord());
        supplierRepository.saveAndFlush(serviceHelper.createSupplierFromDTO(supplierDTO));
        Supplier supplierCheck = supplierRepository.getOne(supplierDTO.getSupplierId());
        return serviceHelper.createDTOFromSupplier(supplierCheck);
    }

//    @Override
//    public SupplierDTO updateRandomData(SupplierDTO supplierDTO) {
//        supplierDTO.setCompanyName(supplierDTO.getCompanyName() + "+" + serviceHelper.generateRandomWord());
//        supplierRepository.update(serviceHelper.createSupplierFromDTO(supplierDTO));
//        Supplier supplierCheck = supplierRepository.get(supplierDTO.getSupplierId());
//        return serviceHelper.createDTOFromSupplier(supplierCheck);
//    }


    /**
     * Обновление екземпляра supplier и передача на запись в БД
     *
     * @param id          - id экземпляра supplier в базе, который необходимо изменить
     * @param supplierDTO - экземпляр supplierDTO, который необходимо изменить
     * @return - Экземпляр supplierDTO
     */
    @Override
    public SupplierDTO update(int id, SupplierDTO supplierDTO) {
        Supplier updateSupplier = supplierRepository.getOne(id);
        log.debug("updatesupplierWithId() Объект supplierDTO передан на обновление: {} ", supplierDTO);
        updateSupplier.setCompanyName(supplierDTO.getCompanyName());
        updateSupplier.setPhone(supplierDTO.getPhone());
        log.info("updatesupplierWithId() Объект supplier успешно обновлен: {} ", updateSupplier);
        supplierRepository.saveAndFlush(updateSupplier);
        Supplier supplierCheck = supplierRepository.getOne(updateSupplier.getSupplierId());
        return serviceHelper.createDTOFromSupplier(supplierCheck);
    }

//    @Override
//    public SupplierDTO update(int id, SupplierDTO supplierDTO) {
//        Supplier updateSupplier = supplierRepository.get(id);
//        log.debug("updatesupplierWithId() Объект supplierDTO передан на обновление: {} ", supplierDTO);
//        updateSupplier.setCompanyName(supplierDTO.getCompanyName());
//        updateSupplier.setPhone(supplierDTO.getPhone());
//        log.info("updatesupplierWithId() Объект supplier успешно обновлен: {} ", updateSupplier);
//        supplierRepository.update(updateSupplier);
//        Supplier supplierCheck = supplierRepository.get(updateSupplier.getSupplierId());
//        return serviceHelper.createDTOFromSupplier(supplierCheck);
//    }

    /**
     * Получение CustomerDTO из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полченного Customer
     */
    @Override
    public SupplierDTO getSupplier(int id) {
        Supplier supplier = supplierRepository.getOne(id);
        return serviceHelper.createDTOFromSupplier(supplier);
    }

//    @Override
//    public SupplierDTO getSupplier(int id) {
//        Supplier supplier = supplierRepository.get(id);
//        return serviceHelper.createDTOFromSupplier(supplier);
//    }

    /**
     * Получение всех CustomerDTO из базы
     *
     * @return - CustomerDTO созданный из полученного Customer
     */
    @Override
    public List<SupplierDTO> getAllSupplier() {
        List<Supplier> customerList = supplierRepository.findAll();
        List<SupplierDTO> customerDTOList = new ArrayList<>();
        for (Supplier supplier : customerList) {
            customerDTOList.add(serviceHelper.createDTOFromSupplier(supplier));
        }
        return customerDTOList;
    }
//
//    @Override
//    public List<SupplierDTO> getAllSupplier() {
//        List<Supplier> customerList = supplierRepository.getAllSupplier();
//        List<SupplierDTO> customerDTOList = new ArrayList<>();
//        for (Supplier supplier : customerList) {
//            customerDTOList.add(serviceHelper.createDTOFromSupplier(supplier));
//        }
//        return customerDTOList;
//    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     */
    @Override
    public SupplierDTO deleteById(int id) {
        SupplierDTO supplierDTO = serviceHelper.createDTOFromSupplier(supplierRepository.getOne(id));
        supplierRepository.deleteById(id);
        return supplierDTO;
    }


}