package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Класс для обработки веб запросов к Customer
 */
public class ServletCustomer extends HttpServlet implements IServlet {

    private final ServletsHelper servletsHelper = new ServletsHelper();

    private final CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутсвия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = servletsHelper.getIdFromRequest(req);
            if (id != 0) {
                CustomerDTO customerCheck = customerServiceImpl.getCustomer(id);
                var jsonString = this.gson.toJson(customerCheck);
                servletsHelper.printJson(jsonString, resp);
            }
        } else {
            var jsonString = gson.toJson(customerServiceImpl.getAllCustomer(), List.class);
            servletsHelper.printJson(jsonString, resp);
        }
    }

    /**
     * Создание нового товара из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var customerDTO = gson.fromJson(servletsHelper.parseJsonToString(req), CustomerDTO.class);
        var customerCheck = customerServiceImpl.create(customerDTO);
        var jsonString = this.gson.toJson(customerCheck);
        servletsHelper.printJson(jsonString, resp);
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = servletsHelper.validateAndGetIdFromRequest(req);
        if (id != 0) {
            var customerDTO = gson.fromJson(servletsHelper.parseJsonToString(req), CustomerDTO.class);
            var customerCheck = customerServiceImpl.update(id, customerDTO);
            var jsonString = this.gson.toJson(customerCheck);
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
            customerServiceImpl.deleteById(id);
        }
    }
}