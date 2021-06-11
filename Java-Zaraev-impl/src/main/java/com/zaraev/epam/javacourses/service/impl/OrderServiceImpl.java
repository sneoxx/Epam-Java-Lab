package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import com.zaraev.epam.javacourses.helper.ServiceHelper;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Сервис для работы с OrderRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;


    private final ServiceHelper serviceHelper = new ServiceHelper();

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @param customerDTO - экземпляр customerDTO
     * @return - OrderDTO конвертированный из Order записанного в базу
     */
    @Override
    public OrderDTO createRandomOrder(CustomerDTO customerDTO, Integer id) {
        Order order = new Order();
        order.setOrderNumber(serviceHelper.getRandomNumber());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(BigDecimal.valueOf(100));
        order.setCustomer(serviceHelper.createCustomerFromDTO(customerDTO));
        Set<Product> products = new HashSet<>();
        Product product = productRepository.getOne(id);
        products.add(product);
        order.setProducts(products);
        orderRepository.saveAndFlush(order);
        Order ordercheck = orderRepository.getOne(order.getOrderId());
        log.debug("createRandomOrder() Объект order успешно записан в БД: {} ", ordercheck);
        return serviceHelper.createDTOFromOrder(ordercheck);
    }

    /**
     * Создание и запись в БД экземпляра order
     *
     * @param orderDTO - Экземпляр orderDTO
     * @return - OrderDTO конвертированный из Order записанного в базу
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setCustomer(customerRepository.getOne(orderDTO.getCustomerId()));
        Set<Product> products = new HashSet<>();
        for (Integer product : orderDTO.getProducts()) {
            Product foundProduct = productRepository.getOne(product);
            products.add(foundProduct);
        }
        order.setProducts(products);
        orderRepository.saveAndFlush(order);
        Order orderCheck = orderRepository.getOne(order.getOrderId());
        log.debug("create() Объект order успешно записан в БД: {} ", orderCheck);
        return serviceHelper.createDTOFromOrder(orderCheck);
    }

    /**
     * Обновление случайными данными и запись в БД екземпляра Order
     *
     * @param order - экземпляр order, на который необходимо изменить
     * @return - - результат операции OrderDTO конвертированный из Order полученного из базы
     */
    @Override
    public OrderDTO updateRandomData(Order order) {
        order.setOrderNumber(order.getOrderNumber() + "+Upd");
        orderRepository.saveAndFlush(order);
        Order orderCheck = orderRepository.getOne(order.getOrderId());
        log.debug("updateRandomData() Объект order успешно обновлен в БД: {} ", orderCheck);
        return serviceHelper.createDTOFromOrder(orderCheck);
    }

    /**
     * Обновление и запись в БД  order
     *
     * @param id       - id экземпляра order в базе, который необходимо изменить
     * @param orderDTO - экземпляр orderDTO, на который необходимо изменить
     * @return - OrderDTO конвертированный из обновленного Order
     */
    @Override
    public OrderDTO update(int id, OrderDTO orderDTO) {
        Order updateOrder = orderRepository.getOne(id);
        updateOrder.setOrderNumber(orderDTO.getOrderNumber());
        updateOrder.setOrderDate(orderDTO.getOrderDate());
        updateOrder.setTotalAmount(orderDTO.getTotalAmount());
        Set<Product> products = new HashSet<>();
        for (Integer product : orderDTO.getProducts()) {
            Product foundProduct = productRepository.getOne(product);
            products.add(foundProduct);
        }
        updateOrder.setProducts(products);
        orderRepository.saveAndFlush(updateOrder);
        Order orderCheck = orderRepository.getOne(updateOrder.getOrderId());
        log.info("update() Объект order успешно обновлен в БД: {} ", orderCheck);
        return serviceHelper.createDTOFromOrder(orderCheck);
    }

    /**
     * Получение Order из базы
     *
     * @param id - id Order, которое необходимло получить
     * @return - OrderDTO созданный из полученного Order
     */
    @Override
    public OrderDTO getOrder(int id) {
        Order order = orderRepository.getOne(id);
        log.debug("getOrder() Объект order успешно получен из БД");
        return serviceHelper.createDTOFromOrder(order);
    }

    /**
     * Получение всех Order из базы
     *
     * @return - коллекция CustomerDTO конвертированная из полученной коллекции Customer
     */
    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(serviceHelper.createDTOFromOrder(order));
        }
        log.debug("getAllOrder() Объекты order успешно получены из БД");
        return orderDTOList;
    }

    /**
     * Удаление Order из базы по id
     *
     * @param id - id Order для удаления
     * @return - OrderDTO конвертированный из удаленного Order
     */
    @Override
    public OrderDTO deleteById(int id) {
        OrderDTO orderDTO = serviceHelper.createDTOFromOrder(orderRepository.getOne(id));
        orderRepository.deleteById(id);
        log.debug("deleteById() Объект order успешно удален из БД");
        return orderDTO;
    }

}