package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки веб запросов к Supplier
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class SupplierResourceImpl implements SupplierResource {

    private final SupplierService supplierService;

    private final ConversionService conversionService;

//    private final SupplierDTOFromSupplierConverter supplierDTOFromSupplierConverter;
//
//    private final SupplierFromSupplierDTOConverter supplierFromSupplierDTOConverter;

    /**
     * Получение поставщика по id переданного в запросе
     *
     * @param id - id из запроса
     * @return - экземпляр SupplierDTO
     */
    @Override
    public SupplierDTO get(int id) {
        Supplier supplierResult = supplierService.getSupplier(id);
        SupplierDTO supplierDTOCheck = conversionService.convert(supplierResult, SupplierDTO.class);
       // SupplierDTO supplierDTOCheck = supplierDTOFromSupplierConverter.convert(supplierService.getSupplier(id));
        log.info("get() - Получен supplier: {}", supplierDTOCheck);
        return supplierDTOCheck;
    }

    /**
     * Получение всех заказов
     *
     * @return - коллекция List SupplierDTO
     */
    @Override
    public List<SupplierDTO> getAll() {
        List<Supplier> supplierList = supplierService.getAllSupplier();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        for (Supplier supplier : supplierList) {
            supplierDTOList.add(conversionService.convert(supplier,SupplierDTO.class));
           // supplierDTOList.add(supplierDTOFromSupplierConverter.convert(supplier));
        }
        log.info("getAll()- Получены все supplier");
        return supplierDTOList;
    }

    /**
     * Создание нового поставщика из переданного json в запросе
     *
     * @param supplierDTO - экземпляр customerDTO для создания
     * @return - созданный supplierDTO
     */
    @Override
    public SupplierDTO create(SupplierDTO supplierDTO) {
        Supplier supplierConvert = conversionService.convert(supplierDTO,Supplier.class);
        Supplier supplierResult = supplierService.create(supplierConvert);
        SupplierDTO supplierDTOCheck = conversionService.convert(supplierResult, SupplierDTO.class);
        //SupplierDTO supplierDTOCheck = supplierDTOFromSupplierConverter.convert(supplierService.create(supplierFromSupplierDTOConverter.convert(supplierDTO)));
        log.info("create() - Создан новый supplier {}", supplierDTOCheck);
        return supplierDTOCheck;
    }

    /**
     * Обновление полей поставщика с определенным id из запроса по данным переданным в json запроса
     *
     * @param id          - id поставщика для обновления
     * @param supplierDTO - supplierDTO  полученный из json запроса
     * @return - обновленный supplierDTO
     */
    @Override
    public SupplierDTO update(int id, SupplierDTO supplierDTO) {
        Supplier supplierConvert = conversionService.convert(supplierDTO,Supplier.class);
        Supplier supplierResult = supplierService.update(id, supplierConvert);
        SupplierDTO supplierDTOCheck = conversionService.convert(supplierResult, SupplierDTO.class);
      //  SupplierDTO supplierDTOCheck = supplierDTOFromSupplierConverter.convert(supplierService.update(id, supplierFromSupplierDTOConverter.convert(supplierDTO)));
        log.info("update() - Обновлен supplier: {}", supplierDTOCheck);
        return supplierDTOCheck;
    }

    /**
     * Удаление поставщика по id переданного в запросе
     * @param id - id удаляемого объекта
     * @return - удаленный объект
     */
    @Override
    public SupplierDTO delete(int id) {
        Supplier supplierResult = supplierService.deleteById(id);
        SupplierDTO supplierDTOCheck = conversionService.convert(supplierResult, SupplierDTO.class);
      //  SupplierDTO supplierDTO = supplierDTOFromSupplierConverter.convert(supplierService.deleteById(id));
        log.info("delete() - Удален supplier {}", supplierDTOCheck);
        return supplierDTOCheck;
    }
}