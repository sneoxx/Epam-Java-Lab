package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.converter.SupplierDTOFromSupplierConverter;
import com.zaraev.epam.javacourses.converter.SupplierFromSupplierDTOConverter;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final SupplierDTOFromSupplierConverter supplierDTOFromSupplierConverter;

    private final SupplierFromSupplierDTOConverter supplierFromSupplierDTOConverter;

    /**
     * Получение поставщика по id переданного в запросе или получение всех поставщиков в случае отсутсвия id
     */
    @Override
    public SupplierDTO get(int id) {
        log.info("get() - Получен supplier по id {}", id);
        return supplierDTOFromSupplierConverter.convert(supplierService.getSupplier(id));
    }

    @Override
    public List<SupplierDTO> getAll(){
        log.info("getAll()- Получены все supplier");
        List<Supplier> supplierList = supplierService.getAllSupplier();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        for (Supplier supplier : supplierList) {
            supplierDTOList.add(supplierDTOFromSupplierConverter.convert(supplier));
        }
        return supplierDTOList;
    }

    /**
     * Создаем нового поставщика из переданного json в запросе
     */
    @Override
    public SupplierDTO create(SupplierDTO supplierDTO) {
        log.info("create() - Создан новый supplier {}", supplierDTO);
        return supplierDTOFromSupplierConverter.convert(supplierService.create(supplierFromSupplierDTOConverter.convert(supplierDTO)));
    }

    /**
     * Обновление полей поставщика из переданного json в запросе
     */
    @Override
    public SupplierDTO update(int id, SupplierDTO supplierDTO)  {
        log.info("update() - Обновлен supplier c id {}", id);
        return supplierDTOFromSupplierConverter.convert(supplierService.update(id, supplierFromSupplierDTOConverter.convert(supplierDTO)));
    }

    /**
     * Удаление поставщика по id переданного в запросе
     */
    @Override
    public SupplierDTO delete(int id) {
        log.info("delete() - Удален supplier с id {}", id);
        return supplierDTOFromSupplierConverter.convert(supplierService.deleteById(id));
    }
}