package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.bufferdata.BufferDataSupplier;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import com.zaraev.epam.javacourses.service.impl.SupplierService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletSupplier extends HttpServlet {

    private final SupplierRepository SUPPLIER_REPOSITORY = new SupplierRepository();

    private final ServletsHelper SERVLET_HELPER = new ServletsHelper();

    private final SupplierService SUPPLIER_SERVICE = new SupplierService();

    private final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение поставщика по id переданного в запросе или получение всех поставщиков в случае отсутсвия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVLET_HELPER.getIdFromRequest(req);
            if (id != 0) {
                Supplier supplier = SUPPLIER_REPOSITORY.getSupplier(id); // получаем объект Supplier по id
                var jsonString = this.GSON.toJson(supplier); //преобразуем в json на основании полей объекта
                SERVLET_HELPER.printJson(jsonString, resp);
            }
        } else {
            var jsonString = GSON.toJson(SUPPLIER_REPOSITORY.getAllSupplier(), List.class);
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }

    /**
     * Создаем нового поставщика из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferDataSupplier bufferDataSupplier = GSON.fromJson(SERVLET_HELPER.parseJsonToString(req), BufferDataSupplier.class);
        Supplier supplier = SUPPLIER_SERVICE.create(bufferDataSupplier);
        var jsonString = this.GSON.toJson(supplier); // преобразуем в json на основании полей объекта
        SERVLET_HELPER.printJson(jsonString, resp);
    }

    /**
     * Обновление полей поставщика из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = SERVLET_HELPER.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var supplier = GSON.fromJson(SERVLET_HELPER.parseJsonToString(req), Supplier.class); // создаем объект из json
            SUPPLIER_SERVICE.updateSupplierWithId(id, supplier);
            var jsonString = this.GSON.toJson(supplier); // преобразуем в json на основании полей объекта
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }

    /**
     * Удаление поствщика по id переданного в запросе
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = SERVLET_HELPER.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var supplier = SUPPLIER_REPOSITORY.getSupplier(id); // получаем объект Supplier по id
            SUPPLIER_REPOSITORY.deleteSupplierWithId(id);
            var jsonString = this.GSON.toJson(supplier); // преобразуем в json на основании полей объекта
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }
}