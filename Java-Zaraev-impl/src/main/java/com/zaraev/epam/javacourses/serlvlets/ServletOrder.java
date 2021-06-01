package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.bufferdata.BufferDataOrder;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.service.impl.OrderService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletOrder extends HttpServlet {

    private final OrderRepository ORDER_REPOSITORY = new OrderRepository();

    private final ServletsHelper SERVLET_HELPER = new ServletsHelper();

    private final OrderService ORDER_SERVICE = new OrderService();

    private final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутствия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVLET_HELPER.getIdFromRequest(req);
            if (id != 0) {
                var order = ORDER_REPOSITORY.getOrder(id);
                var jsonString = this.GSON.toJson(order);
                SERVLET_HELPER.printJson(jsonString, resp);
            }
        } else {
            var jsonString = GSON.toJson(ORDER_REPOSITORY.getAllOrder(), List.class);
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }

    /**
     * Создание нового товара из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferDataOrder bufferDataOrder = GSON.fromJson(SERVLET_HELPER.parseJsonToString(req), BufferDataOrder.class);
        Order order = ORDER_SERVICE.create(bufferDataOrder);
        var jsonString = this.GSON.toJson(order);
        SERVLET_HELPER.printJson(jsonString, resp);
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = SERVLET_HELPER.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var order = GSON.fromJson(SERVLET_HELPER.parseJsonToString(req), Order.class);
            ORDER_SERVICE.updateOrderWithId(id, order);
            var jsonString = this.GSON.toJson(order);
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
            var order = ORDER_REPOSITORY.getOrder(id);
            ORDER_REPOSITORY.deleteOrderWithId(id);
            var jsonString = this.GSON.toJson(order);
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }
}