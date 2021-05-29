package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.ServiceEntity;
import com.zaraev.epam.javacourses.service.ServiceServlets;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletSupplier extends HttpServlet {

    private final ServiceEntity SERVICE_ENTITY = new ServiceEntity();
    private final ServiceServlets SERVICE_SERVLETS = new ServiceServlets();
    private final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение поставщика по id переданного в запросе или получение всех поставщиков в случае отсутсвия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVICE_SERVLETS.getIdFromRequest(req);
            if (id != 0) {
                Supplier supplier = SERVICE_ENTITY.getSupplier(id); // получаем объект Supplier по id
                var jsonString = this.GSON.toJson(supplier); //преобразуем в json на основании полей объекта
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        } else {
            var jsonString = GSON.toJson(SERVICE_ENTITY.getAllSupplier(), List.class);
            SERVICE_SERVLETS.printJson(jsonString, resp);
        }
    }

    /**
     * Создаем нового поставщика из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        var supplier = GSON.fromJson(SERVICE_SERVLETS.parseJsonToString(req), Supplier.class); // создаем объект из json
        SERVICE_ENTITY.createSupplierWithInstance(supplier);
        var jsonString = this.GSON.toJson(supplier); // преобразуем в json на основании полей объекта
        SERVICE_SERVLETS.printJson(jsonString, resp);
    }

    /**
     * Обновление полей поставщика из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVICE_SERVLETS.getIdFromRequest(req);
            if (id != 0) {
                var supplier = GSON.fromJson(SERVICE_SERVLETS.parseJsonToString(req), Supplier.class); // создаем объект из json
                SERVICE_ENTITY.updateSupplierWithId(id, supplier);
                var jsonString = this.GSON.toJson(supplier); // преобразуем в json на основании полей объекта
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        }
    }

    /**
     * Удаление поствщика по id переданного в запросе
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVICE_SERVLETS.getIdFromRequest(req);
            if (id != 0) {
                var supplier = SERVICE_ENTITY.getSupplier(id); // получаем объект Supplier по id
                SERVICE_ENTITY.deleteSupplierWithId(id);
                var jsonString = this.GSON.toJson(supplier); // преобразуем в json на основании полей объекта
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        }
    }
}