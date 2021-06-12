package com.zaraev.epam.javacourses.converter;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrderFromOrderDTOConverter implements Converter<OrderDTO, Order> {

    @Override
    public Order convert(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setOrderNumber(orderDTO.getOrderNumber());
        Customer customer = new Customer();
        customer.setCustomerId(orderDTO.getCustomerId());
        order.setCustomer(customer);
        order.setTotalAmount(orderDTO.getTotalAmount());
        Set<Product> products = new HashSet<>();
        for (Integer product : orderDTO.getProducts()) {
            Product foundProduct = new Product();
            foundProduct.setProductId(product);
            products.add(foundProduct);
        }
        order.setProducts(products);
        return order;
    }
}