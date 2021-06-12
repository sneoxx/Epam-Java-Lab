package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import com.zaraev.epam.javacourses.service.OrderService;
import com.zaraev.epam.javacourses.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс для обработки веб запросов к Order
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class OrderResourceImpl implements OrderResource {

    private final OrderService orderService;

    private final ProductService productService;

    private final ConversionService conversionService;

    /**
     * Получение заказа по id переданного в запросе
     *
     * @param id - id из запроса
     * @return - экземпляр OrderDTO
     */
    @Override
    public OrderDTO get(int id) {
        Order orderResult = orderService.getOrder(id);
        OrderDTO orderDTOCheck = conversionService.convert(orderResult, OrderDTO.class);
        log.info("get() - Получен order: {}", orderDTOCheck);
        return orderDTOCheck;
    }

    /**
     * Получение всех заказов
     *
     * @return - коллекция List orderDTO
     */
    @Override
    public List<OrderDTO> getAll() {
        List<Order> orderList = orderService.getAllOrder();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(conversionService.convert(order, OrderDTO.class));
        }
        log.info("getAll()- Получены все order");
        return orderDTOList;
    }

    /**
     * Создание нового заказа из переданного json в запросе
     *
     * @param orderDTO - экземпляр customerDTO для создания
     * @return - созданный customerDTO
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order orderConvert = conversionService.convert(orderDTO, Order.class);
        Set<Product> products = new HashSet<>();
        for (Product product : orderConvert.getProducts()) {
            Integer foundProduct = product.getProductId();
            products.add(productService.getProduct(foundProduct));
        }
        orderConvert.setProducts(products);
        Order orderResult = orderService.create(orderConvert);
        OrderDTO orderDTOCheck = conversionService.convert(orderResult, OrderDTO.class);
        log.info("create() - Создан новый order {}", orderDTOCheck);
        return orderDTOCheck;
    }

    /**
     * Обновление полей заказа с определенным id из запроса по данным переданным в json запроса
     *
     * @param id       - id заказа для обновления
     * @param orderDTO - orderDTO  полученный из json запроса
     * @return - обновленный orderDTO
     */
    @Override
    public OrderDTO update(int id, OrderDTO orderDTO) {
        Order orderConvert = conversionService.convert(orderDTO, Order.class);
        Set<Product> products = new HashSet<>();
        for (Product product : orderConvert.getProducts()) {
            Integer foundProduct = product.getProductId();
            products.add(productService.getProduct(foundProduct));
        }
        orderConvert.setProducts(products);
        Order orderResult = orderService.update(id, orderConvert);
        OrderDTO orderDTOCheck = conversionService.convert(orderResult, OrderDTO.class);
        log.info("update() - Обновлен order: {}", orderDTOCheck);
        return orderDTOCheck;
    }

    /**
     * Удаление заказа по id переданного в запросе
     *
     * @param id - id удаляемого объекта
     * @return - удаленный объект orderDTO
     */
    @Override
    public OrderDTO delete(int id) {
        Order orderResult = orderService.deleteById(id);
        OrderDTO orderDTOCheck = conversionService.convert(orderResult, OrderDTO.class);
        log.info("delete() - Удален order: {}", orderDTOCheck);
        return orderDTOCheck;
    }
}