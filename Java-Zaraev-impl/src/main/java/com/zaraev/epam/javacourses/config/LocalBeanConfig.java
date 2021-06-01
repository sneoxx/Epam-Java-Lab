package com.zaraev.epam.javacourses.config;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Profile("local")
//@PropertySource("classpath:entity.properties")
@Configuration
public class LocalBeanConfig {

    @Autowired
    private CustomerService customerService;

    @Autowired
   Customer customer;
//
    @Autowired
    Product product;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("WER");
    }

    @Bean
    public Customer customer() {
        var customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("CustomerLocal");
        customer.setPhone("1");
        return customer;
    }

    @Bean
    public Supplier supplier() {
        var supplier = new Supplier();
        supplier.setSupplierId(1);
        supplier.setCompanyName("SupplierLocal");
        supplier.setPhone("1");
        return supplier;
    }

    @Bean
    public Product product() {
        var product = new Product();
        product.setProductId(1);
        product.setProductName("ProductLocal");
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setDiscountinued(false);
        return product;
    }

    @Bean
    public Order order() {
        var order = new Order();
        order.setOrderId(1);
        order.setOrderNumber("1");
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setCustomer(customer);
        order.setTotalAmount(BigDecimal.valueOf(100));
        Set<Product> productSet = new HashSet<>();
        productSet.add(product);
        order.setProducts(productSet);
        return order;
    }

}
