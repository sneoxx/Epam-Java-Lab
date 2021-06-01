package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.service.EService;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements EService {

    @Autowired
    private CustomerRepository customerRepository;// = new CustomerRepository();

    @Autowired
    private ProductRepository productRepository;// = new ProductRepository();

    @Autowired
    private OrderRepository orderRepository;// = new OrderRepository();

    /**
     * Создание случайного Order и передача на запись в БД
     *
     * @param customer - экземпляр customer
     * @return - экземпляр order
     */
    public Order createRandomOrder(Customer customer, Integer id) {
        Order order = new Order();
        order.setOrderNumber(getRandomNumber());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(BigDecimal.valueOf(100));
        order.setCustomer(customer);
        Set<Product> products = new HashSet<>();
        Product product = productRepository.getProduct(id);
        products.add(product);
        order.setProducts(products);
        orderRepository.create(order);
        return order;
    }

    /**
     * Создание и передача на запись в БД
     *
     * @param orderDTO - Экземпляр orderDTO
     * @return - результат опрерации orderDTO полученнй из базы
     */
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = new Order();
        System.out.println(customerRepository.getCustomer(orderDTO.getCustomerId()));
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setCustomer(customerRepository.getCustomer(orderDTO.getCustomerId()));
        Set<Product> products = new HashSet<>();
        for (Integer product : orderDTO.getProducts()) {
            Product foundProduct = productRepository.getProduct(product);
            products.add(foundProduct);
        }
        order.setProducts(products);
        orderRepository.create(order);
        Order orderCheck = orderRepository.getOrderWithInstance(order.getOrderNumber());
        return createOrderDTO(orderCheck);
    }

    /**
     * Обновление экземпляра order и передача на обновление в БД
     *
     * @param order - экземпляр order, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    public OrderDTO update(Order order) {
        order.setOrderNumber(order.getOrderNumber() + "+Upd");
        orderRepository.update(order);
        Order orderCheck = orderRepository.getOrderWithInstance(order.getOrderNumber());
        return createOrderDTO(orderCheck);
    }


    /**
     * Обновление экземпляра order и передача на обновление в БД
     *
     * @param id    - id экземпляра order в базе, который необходимо изменить
     * @param orderDTO- экземпляр orderDTO, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    public OrderDTO updateOrderWithId(int id, OrderDTO orderDTO) {
        Order updateOrder = orderRepository.getOrder(id);
        log.debug("updateProductWithId() Объект orderDTO передан на обновление: {} ", orderDTO);
        updateOrder.setOrderNumber(orderDTO.getOrderNumber());
        updateOrder.setOrderDate(orderDTO.getOrderDate());
        updateOrder.setTotalAmount(orderDTO.getTotalAmount());
        Set<Product> products = new HashSet<>();
        for (Integer product : orderDTO.getProducts()) {
            Product foundProduct = productRepository.getProduct(product);
            products.add(foundProduct);
        }
        updateOrder.setProducts(products);
        log.info("updateProductWithId() Объект order успешно обновлен: {} ", updateOrder);
        orderRepository.update(updateOrder);
        Order orderCheck = orderRepository.getOrderWithInstance(updateOrder.getOrderNumber());
        return createOrderDTO(orderCheck);
    }

    /**
     * Получение CustomerDTO из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полченного Customer
     */
    public OrderDTO getOrder(int id) {
        Order order = orderRepository.getOrder(id);
        return createOrderDTO(order);
    }

    /**
     * Получение всех CustomerDTO из базы
     *
     * @return - CustomerDTO созданный из полученного Customer
     */
    public List<OrderDTO> getAllOrder() {
        List<Order> orderList = orderRepository.getAllOrder();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(createOrderDTO(order));
        }
        return orderDTOList;
    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     */
    public void deleteOrderWithId(int id) {
        orderRepository.deleteOrderWithId(id);
    }

    /**
     * Создание OrderDTO из order)
     *
     * @param order - исходный OrderDTO
     * @return - полученный order
     */
    public OrderDTO createOrderDTO(Order order) {
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

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @return - случайное число
     */
    public String getRandomNumber() {
        return Integer.toString(1 + (int) (Math.random() * 10000));
    }
}