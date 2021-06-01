package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.bufferdata.BufferDataOrder;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class OrderService {

    public CustomerRepository customerRepository = new CustomerRepository();
    public ProductRepository productRepository = new ProductRepository();
    public OrderRepository orderRepository = new OrderRepository();

    /**
     * Создание случайного Order и передача на запись в БД
     *
     * @param customer - экземпляр customer
     * @return - экземпляр order
     */
    public Order createRandomOrder(Customer customer) {
            Order order = new Order();
            order.setOrderNumber(getRandomNumber());
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setTotalAmount(BigDecimal.valueOf(100));
            order.setCustomer(customer);
            orderRepository.create(order);
            return order;
    }

    /**
     * Создание и передача на запись в БД на основании объекта BufferDataOrder c проверкой наличия в базе
     * @param bufferDataOrder - Экземпляр BufferDataOrder
     * @return - экземпляр order
     */
    public Order create (BufferDataOrder bufferDataOrder) {
        Order order = new Order();
        order.setOrderNumber(bufferDataOrder.getOrderNumber());
        order.setOrderDate(bufferDataOrder.getOrderDate());
        order.setTotalAmount(bufferDataOrder.getTotalAmount());
        order.setCustomer(bufferDataOrder.getCustomer());
        Set<Product> products = new HashSet<>();
        Set<Product> tempProducts = bufferDataOrder.getProducts();
        for (Product product : tempProducts) {
            Product foundProduct = productRepository.getProduct(product.getProductId());
            products.add(foundProduct);
        }
        order.setProducts(products);
        orderRepository.create(order);
        return order;
    }

    /**
     * Обновление экземпляра order и передача на обновление в БД
     *
     * @param order - экземпляр order, на который необходимо изменить
     */
    public void update (Order order) {
        order.setOrderNumber(order.getOrderNumber() + "+Upd");
        orderRepository.update(order);
    }

    /**
     * Обновление экземпляра order и передача на обновление в БД
     *
     * @param id    - id экземпляра order в базе, который необходимо изменить
     * @param order - экземпляр order, на который необходимо изменить
     */
    public void updateOrderWithId(int id, Order order) {
        Order updateOrder = orderRepository.getOrder(id);
        log.debug("updateProductWithId() Объект order передан на обновление: {} ", order);
        updateOrder.setOrderNumber(order.getOrderNumber());
        updateOrder.setOrderDate(order.getOrderDate());
        updateOrder.setTotalAmount(order.getTotalAmount());
        log.info("updateProductWithId() Объект order успешно обновлен: {} ", order);
        orderRepository.update(updateOrder);
    }

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @return - случайное число
     */
    public String getRandomNumber() {
        return Integer.toString(1 + (int) (Math.random() * 10000));
    }
}