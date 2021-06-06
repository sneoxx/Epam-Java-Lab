package com.zaraev.epam.javacourses.repository.stub;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.helper.RepositoryHelper;
import com.zaraev.epam.javacourses.repository.IOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;

@Profile("local")
@Component
@Slf4j
public class OrderRepositoryStub implements IOrderRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;// = Persistence.createEntityManagerFactory("WER");

    @Autowired
    private RepositoryHelper repositoryHelper;

    /**
     * Запись в БД екземпляра Order - Ничего не сделает
     *
     * @param order - экземпляр customer для занесения в Order
     * @return - вернет застабленный экземпляр Order
     */
    public Order create(Order order) {
        var stub = repositoryHelper.order();
        log.info("create() Объект order застаблен {}", stub);
        return stub;
    }

    /**
     * Изменение в БД экземпляра order - Ничего не сделает
     *
     * @param order - экземпляр order, который необходимо изменить
     */
    public void update(Order order) {
        log.info("update() Объект order застаблен");
        return;
    }

    /**
     * Получение из БД объекта Order - Ничего не сделает
     *
     * @param id - id объекта Order который необходимо получить
     * @return - вернет застабленный экземпляр Order
     */
    public Order getOrder(int id) {
        var stub = repositoryHelper.order();
        log.info("getstub Объект order застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД объекта Order по полю orderNumber Order - Ничего не сделает
     *
     * @param orderNumber- id объекта Order который необходимо получить
     * @return  - вернет застабленный экземпляр Order
     */
    public Order getOrderWithInstance(String orderNumber) {
        var stub = repositoryHelper.order();
        log.info("getOrderWithInstance() Объект order застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД всех объектов Order - Ничего не сделает
     *
     * @return - Коллекция List из застабленного Order
     */
    public List<Order> getAllOrder() {
        var stub = repositoryHelper.order();
        Order[] orders = {stub};
        List<Order> orderList = Arrays.asList(orders);
        log.info("getAllCustomer() Объект customer застаблен {}", orderList);
        return orderList;
    }

    /**
     * Удаление объекта order из БД - Ничего не сделает
     *
     * @param order - удаляемый объект
     */
    public void deleteOrder(Order order) {
        log.info("create() Объект order застаблен");
        return;
    }

    /**
     * Удаление объекта order из БД по id - Ничего не сделает
     *
     * @param id - id удаляемого order
     */
    public void deleteOrderWithId(int id) {
        log.info("create() Объект order застаблен ");
        return;
    }

}