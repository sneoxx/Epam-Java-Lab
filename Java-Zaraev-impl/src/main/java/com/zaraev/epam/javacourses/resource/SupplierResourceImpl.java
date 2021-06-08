package com.zaraev.epam.javacourses.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.service.impl.SupplierServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Класс для обработки веб запросов к Supplier
 */
@Slf4j
public class SupplierResourceImpl implements SupplierResource {

  //  private final ServletsHelper servletsHelper = new ServletsHelper();

    @Autowired
    private  SupplierServiceImpl supplierService;

  //  private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

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
    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public List<SupplierDTO> getAll(){
        log.info("getAll()- Получены все product");
        return supplierService.getAllSupplier();
    }

    /**
     * Создаем нового поставщика из переданного json в запросе
     */
    @Override
    public SupplierDTO create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var supplierDTO = gson.fromJson(servletsHelper.parseJsonToString(req), SupplierDTO.class);
        var supplierCheck = supplierService.create(supplierDTO);
        var jsonString = this.gson.toJson(supplierCheck);
        servletsHelper.printJson(jsonString, resp);
    }

    /**
     * Обновление полей поставщика из переданного json в запросе
     */
    @Override
    public SupplierDTO update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = servletsHelper.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var supplierDTO = gson.fromJson(servletsHelper.parseJsonToString(req), SupplierDTO.class);
            var supplierCheck = supplierService.update(id, supplierDTO);
            var jsonString = this.gson.toJson(supplierCheck);
            servletsHelper.printJson(jsonString, resp);
        }
    }

    /**
     * Удаление поствщика по id переданного в запросе
     */
    @Override
    public SupplierDTO delete(HttpServletRequest req, HttpServletResponse resp) {
        var id = servletsHelper.validateAndGetIdFromRequest(req);
        if (id != 0) {
            supplierService.deleteById(id);
        }
    }
}