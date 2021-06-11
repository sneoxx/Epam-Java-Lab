package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.OrderDTO;
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

    @Autowired
    private OrderService orderService;

    /**
     * Получение заказа по id переданного в запросе
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
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public OrderDTO delete(@PathVariable("id") int id) {
            log.info("delete() - Удален customer с id {}", id);
            return orderService.deleteById(id);
    }
}