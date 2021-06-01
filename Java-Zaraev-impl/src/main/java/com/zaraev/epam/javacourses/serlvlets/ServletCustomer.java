package com.zaraev.epam.javacourses.serlvlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.bufferdata.BufferDataCustomer;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.service.impl.CustomerService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServletCustomer extends HttpServlet {

    private final CustomerRepository CUSTOMER_REPOSITORY = new CustomerRepository();

    private final ServletsHelper SERVLET_HELPER = new ServletsHelper();

    private final CustomerService CUSTOMER_SERVICE = new CustomerService();

    private final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутсвия id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterNames().hasMoreElements()) {
            var id = SERVLET_HELPER.getIdFromRequest(req);
            if (id != 0) {
                Customer customer = CUSTOMER_REPOSITORY.getCustomer(id);
                var jsonString = this.GSON.toJson(customer);
                SERVLET_HELPER.printJson(jsonString, resp);
            }
        } else {
            var jsonString = GSON.toJson(CUSTOMER_REPOSITORY.getAllCustomer(), List.class);
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }

    /**
     * Создание нового товара из переданного json в запросе
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferDataCustomer bufferDataCustomer = GSON.fromJson(SERVLET_HELPER.parseJsonToString(req), BufferDataCustomer.class);
        Customer customer = CUSTOMER_SERVICE.create(bufferDataCustomer);
        var jsonString = this.GSON.toJson(customer);
        SERVLET_HELPER.printJson(jsonString, resp);
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = SERVLET_HELPER.validateAndGetIdFromRequest(req);
        if (id != 0) {
            Customer customer = GSON.fromJson(SERVLET_HELPER.parseJsonToString(req), Customer.class);
            CUSTOMER_SERVICE.updateCustomerWithId(id, customer);
            var jsonString = this.GSON.toJson(customer);
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
            Customer customer = CUSTOMER_REPOSITORY.getCustomer(id);
            CUSTOMER_REPOSITORY.deleteCustomerWithId(id);
            var jsonString = this.GSON.toJson(customer);
            SERVLET_HELPER.printJson(jsonString, resp);
        }
    }
}