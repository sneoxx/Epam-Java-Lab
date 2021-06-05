package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletProduct extends HttpServlet implements IServlet {

    private final ServletsHelper servletsHelper = new ServletsHelper();

    private final ProductServiceImpl productService = new ProductServiceImpl();

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутсвия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = servletsHelper.getIdFromRequest(req);
            if (id != 0) {
                var productCheck = productService.getProduct(id);
                var jsonString = this.gson.toJson(productCheck);
                servletsHelper.printJson(jsonString, resp);
            }
        } else {
            var jsonString = gson.toJson(productService.getAllProduct(), List.class);
            servletsHelper.printJson(jsonString, resp);
        }
    }

    /**
     * Создаем нового товара из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var productDTO = gson.fromJson(servletsHelper.parseJsonToString(req), ProductDTO.class);
        var productCheck = productService.create(productDTO);
        var jsonString = this.gson.toJson(productCheck);
        servletsHelper.printJson(jsonString, resp);
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = servletsHelper.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var productDTO = gson.fromJson(servletsHelper.parseJsonToString(req), ProductDTO.class);
            var productCheck = productService.updateProductWithId(id, productDTO);
            var jsonString = this.gson.toJson(productCheck);
            servletsHelper.printJson(jsonString, resp);
        }
    }

    /**
     * Удаление товара по id переданного в запросе
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        var id = servletsHelper.validateAndGetIdFromRequest(req);
        if (id != 0) {
            productService.deleteProductWithId(id);
        }
    }
}