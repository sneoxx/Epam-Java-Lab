package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.bufferdata.BufferDataOrder;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.service.ServiceServlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletOrder extends HttpServlet {

   // private final ServiceEntity SERVICE_ENTITY = new ServiceEntity();
    private final OrderRepository ORDER_REPOSITORY = new OrderRepository();
    private final ServiceServlets SERVICE_SERVLETS = new ServiceServlets();
    private final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутствия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVICE_SERVLETS.getIdFromRequest(req);
            if (id != 0) {
                var order = ORDER_REPOSITORY.getOrder(id);
                var jsonString = this.GSON.toJson(order);
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        } else {
            var jsonString = GSON.toJson(ORDER_REPOSITORY.getAllOrder(), List.class);
            SERVICE_SERVLETS.printJson(jsonString, resp);
        }
    }

    /**
     * Создание нового товара из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferDataOrder bufferDataOrder = GSON.fromJson(SERVICE_SERVLETS.parseJsonToString(req), BufferDataOrder.class);
        var order = ORDER_REPOSITORY.createOrderWithInstanceBuf(bufferDataOrder);
        var jsonString = this.GSON.toJson(order);
        SERVICE_SERVLETS.printJson(jsonString, resp);
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        if (req.getParameterNames().hasMoreElements()) {
//            var id = SERVICE_SERVLETS.getIdFromRequest(req);
//            if (id != 0) {
        var id = SERVICE_SERVLETS.validateAndGetIdFromRequest(req);
        if (id != 0) {
                var order = GSON.fromJson(SERVICE_SERVLETS.parseJsonToString(req), Order.class);
                ORDER_REPOSITORY.updateOrderWithId(id, order);
                var jsonString = this.GSON.toJson(order);
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        }


    /**
     * Удаление товара по id переданного в запросе
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        if (req.getParameterNames().hasMoreElements()) {
//            var id = SERVICE_SERVLETS.getIdFromRequest(req);
//            if (id != 0) {
        var id = SERVICE_SERVLETS.validateAndGetIdFromRequest(req);
        if (id != 0) {
                var order = ORDER_REPOSITORY.getOrder(id);
                ORDER_REPOSITORY.deleteOrderWithId(id);
                var jsonString = this.GSON.toJson(order);
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        }
    }
