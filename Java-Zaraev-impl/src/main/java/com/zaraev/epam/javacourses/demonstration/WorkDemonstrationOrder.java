package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.OrderRepository;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.service.EService;
import com.zaraev.epam.javacourses.service.impl.CustomerService;
import com.zaraev.epam.javacourses.service.impl.OrderService;
import com.zaraev.epam.javacourses.service.impl.ProductService;
import com.zaraev.epam.javacourses.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkDemonstrationOrder implements EService {

    @Autowired
    private OrderRepository orderRepository;// = new OrderRepository();

    @Autowired
    private OrderService orderService;// = new OrderService();

    @Autowired
    private CustomerService customerService;// = new CustomerService();

    @Autowired
    private ProductService productService;// = new ProductService();

    @Autowired
    private SupplierService supplierService;// = new SupplierService();

    @Autowired
    private ProductRepository productRepository;// = new ProductRepository();

    /**
     * Метод для демонстрации работы операций CRUD класса Order
     */
    public void testOrder() {
        Customer customer = customerService.createRandomCustomer();
        Customer customer1 = customerService.createRandomCustomer();
        Supplier supplier = supplierService.createRandomSupplier();
        Product product = productService.createRandomProduct(supplier);
        Product product1 = productService.createRandomProduct(supplier);
        Order order = orderService.createRandomOrder(customer, 1);
        Order order1 = orderService.createRandomOrder(customer1,2);
       orderRepository.getOrder(1);
        customerService.update(customer);
        orderRepository.deleteOrder(order1);
    }
}