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
     * Создание случайного Order и передача на запись в БД
     *
     * @param customerDTO - экземпляр customer
     * @return - экземпляр order
     */
    @Override
    public OrderDTO createRandomOrder(CustomerDTO customerDTO, Integer id) {
        Order order = new Order();
        order.setOrderNumber(serviceHelper.getRandomNumber());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(BigDecimal.valueOf(100));
        order.setCustomer(serviceHelper.createCustomerFromDTO(customerDTO));
        Set<Product> products = new HashSet<>();
        Product product = productRepository.get(id);
        System.out.println(product + "ttyd");
        products.add(product);
        order.setProducts(products);
        Order order1 = orderRepository.create(order);
        System.out.println(order1.getProducts()+ "ttyde1");
        return serviceHelper.createDTOFromOrder(order1);
    }



    /**
     * Создание и передача на запись в БД
     *
     * @param orderDTO - Экземпляр orderDTO
     * @return - результат опрерации orderDTO полученнй из базы
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = new Order();
        System.out.println(customerRepository.get(orderDTO.getCustomerId()));
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setCustomer(customerRepository.get(orderDTO.getCustomerId()));
        Set<Product> products = new HashSet<>();
        for (Integer product : orderDTO.getProducts()) {
            Product foundProduct = productRepository.get(product);
            products.add(foundProduct);
        }
        order.setProducts(products);
        orderRepository.create(order);
        Order orderCheck = orderRepository.get(order.getOrderId());
        return serviceHelper.createDTOFromOrder(orderCheck);
    }

    /**
     * Обновление экземпляра order и передача на обновление в БД
     *
     * @param order - экземпляр order, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    @Override
    public OrderDTO updateRandomData(Order order) {
        order.setOrderNumber(order.getOrderNumber() + "+Upd");
        orderRepository.update(order);
        Order orderCheck = orderRepository.get(order.getOrderId());
        return serviceHelper.createDTOFromOrder(orderCheck);
    }

    /**
     * Обновление экземпляра order и передача на обновление в БД
     *
     * @param id        - id экземпляра order в базе, который необходимо изменить
     * @param orderDTO- экземпляр orderDTO, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    @Override
    public OrderDTO update(int id, OrderDTO orderDTO) {
        Order updateOrder = orderRepository.get(id);
        log.debug("updateProductWithId() Объект orderDTO передан на обновление: {} ", orderDTO);
        updateOrder.setOrderNumber(orderDTO.getOrderNumber());
        updateOrder.setOrderDate(orderDTO.getOrderDate());
        updateOrder.setTotalAmount(orderDTO.getTotalAmount());
        Set<Product> products = new HashSet<>();
        for (Integer product : orderDTO.getProducts()) {
            Product foundProduct = productRepository.get(product);
            products.add(foundProduct);
        }
        updateOrder.setProducts(products);
        log.info("updateProductWithId() Объект order успешно обновлен: {} ", updateOrder);
        orderRepository.update(updateOrder);
        Order orderCheck = orderRepository.get(updateOrder.getOrderId());
        return serviceHelper.createDTOFromOrder(orderCheck);
    }

    /**
     * Получение CustomerDTO из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полченного Customer
     */
    @Override
    public OrderDTO getOrder(int id) {
        Order order = orderRepository.get(id);
        return serviceHelper.createDTOFromOrder(order);
    }

    /**
     * Получение всех CustomerDTO из базы
     *
     * @return - CustomerDTO созданный из полученного Customer
     */
    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orderList = orderRepository.getAllOrder();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(serviceHelper.createDTOFromOrder(order));
        }
        return orderDTOList;
    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     */
    @Override
    public void deleteOrderWithId(int id) {
        orderRepository.delete(id);
    }

}