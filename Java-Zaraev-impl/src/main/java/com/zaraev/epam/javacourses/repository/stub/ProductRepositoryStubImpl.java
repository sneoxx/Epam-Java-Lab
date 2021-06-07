package com.zaraev.epam.javacourses.repository.stub;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Класс для возврата застабленных значения класса Product без обращения к самой БД
 */
@Profile("local")
@Component
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryStubImpl implements ProductRepository {

    private final MessageSource messageSource;

    @Value("${locale:en}")
    private Locale locale;

    /**
     * Запись в БД екземпляра Product - Ничего не сделает
     *
     * @param product - экземпляр supplier для занесения в Product
     * @return - вернет застабленный экземпляр Product
     */
    @Override
    public Product create(Product product) {
        var stub = getStubProduct();
        log.info("create() Объект product застаблен {}", stub);
        return stub;
    }

    /**
     * Изменение в БД экземпляра product - Ничего не сделает
     *
     * @param product - экземпляр product, который необходимо изменить
     */
    @Override
    public Product update(Product product) {
        var stub = getStubProduct();
        log.info("update() Объект product застаблен");
        return stub;
    }

    /**
     * Получение из БД объекта Product - Ничего не сделает
     *
     * @param id - id объекта Product который необходимо получить
     * @return - вернет застабленный экземпляр Product
     */
    @Override
    public Product get(int id) {
        var stub = getStubProduct();
        log.info("getProduct() Объект product застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД всех объектов Product - Ничего не сделает
     *
     * @return - Коллекция List из застабленного Product
     */
    @Override
    public List<Product> getAllProduct() {
        var stub = getStubProduct();
        Product[] products = {stub};
        List<Product> productListList = Arrays.asList(products);
        log.info("getAllCustomer() Объект customer застаблен {}", productListList);
        return productListList;
    }


    /**
     * Удаление объекта Product из БД по id - Ничего не сделает
     *
     * @param id- id удаляемого Product
     */
    @Override
    public void delete(int id) {
        log.info("deleteProductWithId() Объект product застаблен");
    }

    private Customer getStubCustomer() {
        var customer = new Customer();
        customer.setCustomerId(Integer.parseInt(messageSource.getMessage("customerId", null, "1", locale)));
        customer.setCustomerName(messageSource.getMessage("customerName", null, "Error", locale));
        customer.setPhone(messageSource.getMessage("customerPhone", null, "Error", locale));
        return customer;
    }

    private Supplier getStubSupplier() {
        var supplier = new Supplier();
        supplier.setSupplierId(1);
        supplier.setCompanyName("supplierCompanyName");
        supplier.setPhone("111111");
        return supplier;
    }

    private Product getStubProduct() {
        var product = new Product();
        product.setProductId(1);
        product.setProductName("productName");
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setDiscountinued(true);
        product.setSupplier(getStubSupplier());
        Set<Order> orderSet = new HashSet<>();
        orderSet.add(getStubOrder());
        product.setOrders(orderSet);
        return product;
    }

    public Order getStubOrder() {
        var order = new Order();
        order.setOrderId(1);
        order.setOrderNumber("orderNumber");
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setCustomer(getStubCustomer());
        order.setTotalAmount(BigDecimal.valueOf(100));
        return order;
    }


}