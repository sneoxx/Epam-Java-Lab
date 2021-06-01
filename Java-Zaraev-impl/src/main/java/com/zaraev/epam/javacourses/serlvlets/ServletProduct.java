package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.repositiry.ServiceEntity;
import com.zaraev.epam.javacourses.service.ServiceServlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletProduct extends HttpServlet {

    private final ServiceEntity SERVICE_ENTITY = new ServiceEntity();
    private final ServiceServlets SERVICE_SERVLETS = new ServiceServlets();
    private final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутсвия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVICE_SERVLETS.getIdFromRequest(req);
            if (id != 0) {
                var product = SERVICE_ENTITY.getProduct(id);
                var jsonString = this.GSON.toJson(product);
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        } else {
            var jsonString = GSON.toJson(SERVICE_ENTITY.getAllProduct(), List.class);
            SERVICE_SERVLETS.printJson(jsonString, resp);
        }
    }

    /**
     * Создаем нового товара из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var product = GSON.fromJson(SERVICE_SERVLETS.parseJsonToString(req), Product.class);
        SERVICE_ENTITY.createProductWithInstance(product);
        var jsonString = this.GSON.toJson(product);
        SERVICE_SERVLETS.printJson(jsonString, resp);
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVICE_SERVLETS.getIdFromRequest(req);
            if (id != 0) {
                var product = GSON.fromJson(SERVICE_SERVLETS.parseJsonToString(req), Product.class);
                SERVICE_ENTITY.updateProductWithId(id, product);
                var jsonString = this.GSON.toJson(product);
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        }
    }

    /**
     * Удаление товара по id переданного в запросе
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVICE_SERVLETS.getIdFromRequest(req);
            if (id != 0) {
                var product = SERVICE_ENTITY.getProduct(id);
                SERVICE_ENTITY.deleteProductWithId(id);
                var jsonString = this.GSON.toJson(product);
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        }
    }
}