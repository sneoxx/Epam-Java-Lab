package com.zaraev.epam.javacourses.converter;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Конвертер из Order в OrderDTO
 */
@Component
public class OrderDTOFromOrderConverter implements Converter<Order, OrderDTO> {

    @Override
    public OrderDTO convert(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setCustomerId(order.getCustomer().getCustomerId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        Set<Integer> products = new HashSet<>();
        for (Product product : order.getProducts()) {
            Integer foundProduct = product.getProductId();
            products.add(foundProduct);
        }
        orderDTO.setProducts(products);
        return orderDTO;
    }
}