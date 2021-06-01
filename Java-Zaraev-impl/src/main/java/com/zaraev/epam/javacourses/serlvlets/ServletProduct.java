package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.bufferdata.BufferDataProduct;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.service.impl.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletProduct extends HttpServlet {

    private final ProductRepository PRODUCT_REPOSITORY = new ProductRepository();

    private final ServletsHelper SERVLET_HELPER = new ServletsHelper();

    private final ProductService PRODUCT_SERVICE = new ProductService();

    private final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутсвия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVLET_HELPER.getIdFromRequest(req);
            if (id != 0) {
                var product = PRODUCT_REPOSITORY.getProduct(id);
                var jsonString = this.GSON.toJson(product);
                SERVLET_HELPER.printJson(jsonString, resp);
            }
        } else {
            var jsonString = GSON.toJson(PRODUCT_REPOSITORY.getAllProduct(), List.class);
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }

    /**
     * Создаем нового товара из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferDataProduct bufferDataProduct = GSON.fromJson(SERVLET_HELPER.parseJsonToString(req), BufferDataProduct.class);
        Product product = PRODUCT_SERVICE.create(bufferDataProduct);
        var jsonString = this.GSON.toJson(product);
        SERVLET_HELPER.printJson(jsonString, resp);
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = SERVLET_HELPER.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var product = GSON.fromJson(SERVLET_HELPER.parseJsonToString(req), Product.class);
            PRODUCT_SERVICE.updateProductWithId(id, product);
            var jsonString = this.GSON.toJson(product);
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }

    /**
     * Удаление товара по id переданного в запросе
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = SERVLET_HELPER.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var product = PRODUCT_REPOSITORY.getProduct(id);
            PRODUCT_REPOSITORY.deleteProductWithId(id);
            var jsonString = this.GSON.toJson(product);
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }
}