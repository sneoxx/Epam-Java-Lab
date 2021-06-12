package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.converter.OrderDTOFromOrderConverter;
import com.zaraev.epam.javacourses.converter.OrderFromOrderDTOConverter;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import com.zaraev.epam.javacourses.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки веб запросов к Order
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class OrderResourceImpl implements OrderResource {

    private final OrderService orderService;

    private final OrderDTOFromOrderConverter orderDTOFromOrderConverter;
    
    private final OrderFromOrderDTOConverter orderFromOrderDTOConverter;

    /**
     * Получение заказа по id переданного в запросе
     */
    @Override
    public OrderDTO get(int id) {
        log.info("get() - Получен order по id {}", id);
        return orderDTOFromOrderConverter.convert(orderService.getOrder(id));
    }

    /**
     * Получение всех заказов
     */
    @Override
    public List<OrderDTO> getAll() {
        log.info("getAll()- Получены все order");
        List<Order> orderList = orderService.getAllOrder();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(orderDTOFromOrderConverter.convert(order));
        }
        return orderDTOList;
    }

    /**
     * Создание нового заказа из переданного json в запросе
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO)  {
        log.info("create() - Создан новый order {}", orderDTO);
        return  orderDTOFromOrderConverter.convert(orderService.create(orderFromOrderDTOConverter.convert(orderDTO)));
    }

    /**
     * Обновление полей заказа из переданного json в запросе
     */
    @Override
    public OrderDTO update(int id, OrderDTO orderDTO) {
        log.info("update() - Обновлен order c id {}", id);
        return  orderDTOFromOrderConverter.convert(orderService.update(id, orderFromOrderDTOConverter.convert(orderDTO)));
    }

    /**
     * Удаление заказа по id переданного в запросе
     */
    @Override
    public OrderDTO delete(int id) {
            log.info("delete() - Удален customer с id {}", id);
            return  orderDTOFromOrderConverter.convert(orderService.deleteById(id));
    }
}