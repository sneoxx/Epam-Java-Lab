package com.zaraev.epam.javacourses.repository.stub;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Класс для возврата застабленных значения класса Order без обращения к самой БД
 */
@Profile("local")
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryStubImpl implements OrderRepository {

    private final MessageSource messageSource;

    @Value("${locale:en}")
    @Autowired
    private Locale locale;

    /**
     * Запись в БД екземпляра Order - Ничего не сделает
     *
     * @param order - экземпляр customer для занесения в Order
     * @return - вернет застабленный экземпляр Order
     */
    public Order create(Order order) {
        var stub = getStubOrder();
        log.info("create() Объект order застаблен {}", stub);
        return stub;
    }

    /**
     * Изменение в БД экземпляра order - Ничего не сделает
     *
     * @param order - экземпляр order, который необходимо изменить
     */
    public Order update(Order order) {
        var stub = getStubOrder();
        log.info("update() Объект order застаблен");
        return stub;
    }

    /**
     * Получение из БД объекта Order - Ничего не сделает
     *
     * @param id - id объекта Order который необходимо получить
     * @return - вернет застабленный экземпляр Order
     */
    public Order get(int id) {
        var stub = getStubOrder();
        log.info("getstub Объект order застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД всех объектов Order - Ничего не сделает
     *
     * @return - Коллекция List из застабленного Order
     */
    public List<Order> getAllOrder() {
        var stub = getStubOrder();
        Order[] orders = {stub};
        List<Order> orderList = Arrays.asList(orders);
        log.info("getAllCustomer() Объект customer застаблен {}", orderList);
        return orderList;
    }

    /**
     * Удаление объекта order из БД по id - Ничего не сделает
     *
     * @param id - id удаляемого order
     */
    public void delete(int id) {
        log.info("create() Объект order застаблен ");
    }

    private Order getStubOrder() {
        var order = new Order();
        order.setOrderId(1);
        order.setOrderNumber("orderNumber");
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setCustomer(customer());
        order.setTotalAmount(BigDecimal.valueOf(100));
        return order;
    }

    public Customer customer() {
        var customer = new Customer();
        customer.setCustomerId(Integer.parseInt(messageSource.getMessage("customerId", null, "1", locale)));
        customer.setCustomerName(messageSource.getMessage("customerName", null, "Error", locale));
        customer.setPhone(messageSource.getMessage("customerPhone", null, "Error", locale));
        return customer;
    }

}