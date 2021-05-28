package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.service.ServiceEntity;
import com.zaraev.epam.javacourses.service.ServiceServlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletCustomer extends HttpServlet {

    private final ServiceEntity SERVICE_ENTITY = new ServiceEntity();
    private final ServiceServlets SERVICE_SERVLETS = new ServiceServlets();
    private final Gson GSON = new Gson();

    /**
     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутсвия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVICE_SERVLETS.getIdFromRequest(req);
            if (id != 0) {
                Customer Customer = SERVICE_ENTITY.getCustomer(id);
                var jsonString = this.GSON.toJson(Customer);
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        } else {
            var jsonString = GSON.toJson(SERVICE_ENTITY.getAllCustomer(), List.class);
            SERVICE_SERVLETS.printJson(jsonString, resp);
        }
    }

    /**
     * Создаем нового товара из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Customer customer = GSON.fromJson(SERVICE_SERVLETS.parseJsonToString(req), Customer.class);
        //  customer.setCustomerId(null);
        SERVICE_ENTITY.createCustomerWithInstance(customer);
        var jsonString = this.GSON.toJson(customer);
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
                Customer customer = GSON.fromJson(SERVICE_SERVLETS.parseJsonToString(req), Customer.class);
                SERVICE_ENTITY.updateCustomerWithId(id, customer);
                var jsonString = this.GSON.toJson(customer);
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
                Customer customer = SERVICE_ENTITY.getCustomer(id);
                SERVICE_ENTITY.deleteCustomerWithId(id);
                var jsonString = this.GSON.toJson(customer);
                SERVICE_SERVLETS.printJson(jsonString, resp);
            }
        }
    }
}