package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.service.impl.SupplierServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletSupplier extends HttpServlet implements IServlet {

    private final ServletsHelper servletsHelper = new ServletsHelper();

    private final SupplierServiceImpl supplierService = new SupplierServiceImpl();

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение поставщика по id переданного в запросе или получение всех поставщиков в случае отсутсвия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = servletsHelper.getIdFromRequest(req);
            if (id != 0) {
                var supplierCheck = supplierService.getSupplier(id);
                var jsonString = this.gson.toJson(supplierCheck);
                servletsHelper.printJson(jsonString, resp);
            }
        } else {
            var jsonString = gson.toJson(supplierService.getAllSupplier(), List.class);
            servletsHelper.printJson(jsonString, resp);
        }
    }

    /**
     * Создаем нового поставщика из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var supplierDTO = gson.fromJson(servletsHelper.parseJsonToString(req), SupplierDTO.class);
        var supplierCheck = supplierService.create(supplierDTO);
        var jsonString = this.gson.toJson(supplierCheck);
        servletsHelper.printJson(jsonString, resp);
    }

    /**
     * Обновление полей поставщика из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = servletsHelper.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var supplierDTO = gson.fromJson(servletsHelper.parseJsonToString(req), SupplierDTO.class);
            var supplierCheck = supplierService.updateSupplierWithId(id, supplierDTO);
            var jsonString = this.gson.toJson(supplierCheck);
            servletsHelper.printJson(jsonString, resp);
        }
    }

    /**
     * Удаление поствщика по id переданного в запросе
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        var id = servletsHelper.validateAndGetIdFromRequest(req);
        if (id != 0) {
            supplierService.deleteSupplierWithId(id);
        }
    }
}