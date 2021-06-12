package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Сервис для работы с OrderRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @param customer - экземпляр customerDTO
     * @return - OrderDTO конвертированный из Order записанного в базу
     */
    @Override
    public Order createRandomOrder(Customer customer, Integer id) {
        Order order = new Order();
        order.setOrderNumber(getRandomNumber());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(BigDecimal.valueOf(100));
        order.setCustomer(customer);
        Set<Product> products = new HashSet<>();
        Product product = productRepository.getOne(id);
        products.add(product);
        order.setProducts(products);
        Order ordercheck = orderRepository.save(order);
        log.debug("createRandomOrder() Объект order успешно записан в БД: {} ", ordercheck);
        return ordercheck;
    }

    /**
     * Создание и запись в БД экземпляра order
     *
     * @param order - Экземпляр orderDTO
     * @return - OrderDTO конвертированный из Order записанного в базу
     */
    @Override
    public Order create(Order order) {
        Order orderCheck = orderRepository.save(order);
        log.debug("create() Объект order успешно записан в БД: {} ", orderCheck);
        return orderCheck;
    }

    /**
     * Обновление случайными данными и запись в БД екземпляра Order
     *
     * @param order - экземпляр order, на который необходимо изменить
     * @return - - результат операции OrderDTO конвертированный из Order полученного из базы
     */
    @Override
    public Order updateRandomData(Order order) {
        order.setOrderNumber(order.getOrderNumber() + "+Upd");
        Order orderCheck = orderRepository.save(order);
        log.debug("updateRandomData() Объект order успешно обновлен в БД: {} ", orderCheck);
        return orderCheck;
    }

    /**
     * Обновление и запись в БД  order
     *
     * @param id       - id экземпляра order в базе, который необходимо изменить
     * @param order - экземпляр orderDTO, на который необходимо изменить
     * @return - OrderDTO конвертированный из обновленного Order
     */
    @Override
    public Order update(int id, Order order) {
        Order updateOrder = orderRepository.getOne(id);
        updateOrder.setOrderNumber(order.getOrderNumber());
        updateOrder.setOrderDate(order.getOrderDate());
        updateOrder.setTotalAmount(order.getTotalAmount());
        updateOrder.setProducts(order.getProducts());
        Order orderCheck = orderRepository.save(updateOrder);
        log.info("update() Объект order успешно обновлен в БД: {} ", orderCheck);
        return orderCheck;
    }

    /**
     * Получение Order из базы
     *
     * @param id - id Order, которое необходимло получить
     * @return - OrderDTO созданный из полученного Order
     */
    @Override
    public Order getOrder(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            log.debug("getOrder() Объект order успешно получен из БД: {}", order);
            return optionalOrder.get();
        }
        log.debug("getProduct() Объект order не найден, создан новый Order");
        return new Order();
    }

    /**
     * Получение всех Order из базы
     *
     * @return - коллекция CustomerDTO конвертированная из полученной коллекции Customer
     */
    @Override
    public List<Order> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        log.debug("getAllOrder() Объекты order успешно получены из БД: {}", orderList);
        return orderList;
    }

    /**
     * Удаление Order из базы по id
     *
     * @param id - id Order для удаления
     * @return - OrderDTO конвертированный из удаленного Order
     */
    @Override
    public Order deleteById(int id) {
        Order orderCheck = orderRepository.getOne(id);
        orderRepository.deleteById(id);
        log.debug("deleteById() Объект order успешно удален из БД: {}", orderCheck);
        return orderCheck;
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