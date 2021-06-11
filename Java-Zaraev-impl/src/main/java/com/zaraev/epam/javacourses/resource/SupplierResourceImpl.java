package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс для обработки веб запросов к Supplier
 */
@RestController
@Slf4j
public class SupplierResourceImpl implements SupplierResource {

    @Autowired
    private SupplierService supplierService;

    /**
     * Получение поставщика по id переданного в запросе или получение всех поставщиков в случае отсутсвия id
     */
    @Override
    @RequestMapping(value = "/supplier/{id}", method = RequestMethod.GET)
    public SupplierDTO get(@PathVariable("id") int id) {
        log.info("get() - Получен supplier по id {}", id);
        return supplierService.getSupplier(id);
    }

    @Override
    @RequestMapping(value = "/supplier", method = RequestMethod.GET)
    public List<SupplierDTO> getAll(){
        log.info("getAll()- Получены все supplier");
        return supplierService.getAllSupplier();
    }

    /**
     * Создаем нового поставщика из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/supplier", method = RequestMethod.POST)
    public SupplierDTO create(@RequestBody SupplierDTO supplierDTO) {
        log.info("create() - Создан новый supplier {}", supplierDTO);
        return supplierService.create(supplierDTO);
    }

    /**
     * Обновление полей поставщика из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/supplier/{id}", method = RequestMethod.PUT)
    public SupplierDTO update(@PathVariable("id") int id, @RequestBody SupplierDTO supplierDTO)  {
        log.info("update() - Обновлен supplier c id {}", id);
        return supplierService.update(id, supplierDTO);
    }

    /**
     * Удаление поставщика по id переданного в запросе
     */
    @Override
    @RequestMapping(value = "/supplier/{id}", method = RequestMethod.DELETE)
    public SupplierDTO delete(@PathVariable("id") int id) {
        log.info("delete() - Удален supplier с id {}", id);
        return supplierService.deleteById(id);
    }
}