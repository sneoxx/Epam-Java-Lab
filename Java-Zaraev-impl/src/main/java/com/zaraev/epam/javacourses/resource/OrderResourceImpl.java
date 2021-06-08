package com.zaraev.epam.javacourses.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import com.zaraev.epam.javacourses.helper.ServletsHelper;
import com.zaraev.epam.javacourses.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс для обработки веб запросов к Order
 */
@RestController
@Slf4j
public class OrderResourceImpl implements OrderResource {

    private final ServletsHelper servletsHelper = new ServletsHelper();

    @Autowired
    private OrderService orderService;

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /**
     * Получение заказ по id переданного в запросе
     */
    @Override
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public OrderDTO get(@PathVariable("id") int id) {
        log.info("get() - Получен order по id {}", id);
        return orderService.getOrder(id);
    }

    /**
     * Получение всех заказов
     */
    @Override
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public List<OrderDTO> getAll() {
        log.info("getAll()- Получены все order");
        return orderService.getAllOrder();
    }

    /**
     * Создание нового заказа из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public OrderDTO create(@RequestBody OrderDTO orderDTO)  {
        log.info("create() - Создан новый order {}", orderDTO);
        return orderService.create(orderDTO);
    }

    /**
     * Обновление полей заказа из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public OrderDTO update(@PathVariable("id") int id, @RequestBody OrderDTO orderDTO) {
        log.info("update() - Обновлен order c id {}", id);
        return orderService.update(id, orderDTO);
    }

    /**
     * Удаление заказа по id переданного в запросе
     */
    @Override
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public OrderDTO delete(@PathVariable("id") int id) {
            log.info("delete() - Удален customer с id {}", id);
            return orderService.deleteById(id);
    }
}

//
//    /**
//     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутствия id
//     */
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        if (req.getParameterNames().hasMoreElements()) {
//            var id = servletsHelper.getIdFromRequest(req);
//            if (id != 0) {
//                OrderDTO orderCheck = orderServiceImpl.getOrder(id);
//                var jsonString = this.gson.toJson(orderCheck);
//                servletsHelper.printJson(jsonString, resp);
//            }
//        } else {
//            var jsonString = gson.toJson(orderServiceImpl.getAllOrder(), List.class);
//            servletsHelper.printJson(jsonString, resp);
//        }
//    }
//
//    /**
//     * Создание нового товара из переданного json в запросе
//     */
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        OrderDTO orderDTO = gson.fromJson(servletsHelper.parseJsonToString(req), OrderDTO.class);
//        OrderDTO orderCheck = orderServiceImpl.create(orderDTO);
//        var jsonString = this.gson.toJson(orderCheck);
//        servletsHelper.printJson(jsonString, resp);
//    }
//
//    /**
//     * Обновление полей товара из переданного json в запросе
//     */
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        var id = servletsHelper.validateAndGetIdFromRequest(req);
//        if (id != 0) {
//            var orderDTO = gson.fromJson(servletsHelper.parseJsonToString(req), OrderDTO.class);
//            var orderCheck = orderServiceImpl.update(id, orderDTO);
//            var jsonString = this.gson.toJson(orderCheck);
//            servletsHelper.printJson(jsonString, resp);
//        }
//    }
//
//    /**
//     * Удаление товара по id переданного в запросе
//     */
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
//        var id = servletsHelper.validateAndGetIdFromRequest(req);
//        if (id != 0) {
//            orderServiceImpl.deleteById(id);
//        }
//    }