package com.zaraev.epam.javacourses.helper;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс с методами для создания застабленных сущностей
 */
@Component
public class RepositoryHelper {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleStub locale;

    public Customer customer() {
        var customer = new Customer();
        customer.setCustomerId(Integer.parseInt(messageSource.getMessage("customerId", null, "1", locale.getLocale())));
        customer.setCustomerName(messageSource.getMessage("customerName", null, "Error", locale.getLocale()));
        customer.setPhone(messageSource.getMessage("customerPhone", null, "Error", locale.getLocale()));
        return customer;
    }

        public Supplier supplier() {
        var supplier = new Supplier();
        supplier.setSupplierId(1);
        supplier.setCompanyName("supplierCompanyName");
        supplier.setPhone("111111");
        return supplier;
    }

    public Order order() {
        var order = new Order();
        order.setOrderId(1);
        order.setOrderNumber("orderNumber");
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setCustomer(customer());
        order.setTotalAmount(BigDecimal.valueOf(100));
        return order;
    }

    public Product product() {
        var product = new Product();
        product.setProductId(1);
        product.setProductName("productName");
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setDiscountinued(true);
        product.setSupplier(supplier());
        Set<Order> orderSet = new HashSet<>();
        orderSet.add(order());
        product.setOrders(orderSet);
        return product;
    }
}
