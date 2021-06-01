package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.service.impl.OrderService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletOrder extends HttpServlet {

    private final ServletsHelper servletsHelper = new ServletsHelper();

    private final OrderService orderService = new OrderService();

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутствия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = servletsHelper.getIdFromRequest(req);
            if (id != 0) {
                OrderDTO orderCheck = orderService.getOrder(id);
                var jsonString = this.gson.toJson(orderCheck);
                servletsHelper.printJson(jsonString, resp);
            }
        } else {
            var jsonString = gson.toJson(orderService.getAllOrder(), List.class);
            servletsHelper.printJson(jsonString, resp);
        }
    }

    /**
     * Создание нового товара из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OrderDTO orderDTO = gson.fromJson(servletsHelper.parseJsonToString(req), OrderDTO.class);
        OrderDTO orderCheck = orderService.create(orderDTO);
        var jsonString = this.gson.toJson(orderCheck);
        servletsHelper.printJson(jsonString, resp);
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = servletsHelper.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var orderDTO = gson.fromJson(servletsHelper.parseJsonToString(req), OrderDTO.class);
            var orderCheck = orderService.updateOrderWithId(id, orderDTO);
            var jsonString = this.gson.toJson(orderCheck);
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
            orderService.deleteOrderWithId(id);
        }
    }
}