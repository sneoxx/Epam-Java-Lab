package com.zaraev.epam.javacourses.helper;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.dto.OrderDTO;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.repository.SupplierRepository;

import java.util.Random;

/**
 * Класс с общими методами для классов Service
 */
public class ServiceHelper {

    /**
     * Генерация случайного слова
     *
     * @return - случайное слово
     */
    public String generateRandomWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(2) + 3];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @return - случайное число
     */
    public String getRandomNumber() {
        return Integer.toString(1 + (int) (Math.random() * 10000));
    }

    /**
     * Создание сustomerDTO из customer
     *
     * @param customer - исходный supplier
     * @return - полученный сustomerDTO
     */
    public CustomerDTO createDTOFromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setCustomerId(customer.getCustomerId());
        return customerDTO;
    }

    /**
     * Создание сustomerDTO из customer
     *
     * @param customerDTO - исходный supplierDTO
     * @return - полученный сustomer
     */
    public Customer createCustomerFromDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPhone(customerDTO.getPhone());
        customer.setCustomerId(customerDTO.getCustomerId());
        return customer;
    }

    /**
     * Создание OrderDTO из order
     *
     * @param order - исходный Order
     * @return - полученный order
     */
    public OrderDTO createDTOFromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setCustomerId(order.getCustomer().getCustomerId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        return orderDTO;
    }


    /**
     * Создание ProductDTO из product
     *
     * @param product - исходный product
     * @return - полученный ProductDTO
     */
    public ProductDTO createDTOFromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setDiscountinued(product.isDiscountinued());
        productDTO.setUnitPrice(product.getUnitPrice());
        productDTO.setSupplierId(product.getSupplier().getSupplierId());
        return productDTO;
    }

    /**
     * Создание ProductDTO из product
     *
     * @param productDTO - исходный product
     * @return - полученный ProductDTO
     */
    public Product createProductFromDTO(ProductDTO productDTO,SupplierRepository supplierRepository) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setDiscountinued(productDTO.isDiscountinued());
        product.setUnitPrice(productDTO.getUnitPrice());
        System.out.println(productDTO.getSupplierId());
        product.setSupplier(supplierRepository.get(productDTO.getSupplierId()));
        return product;
    }


    /**
     * Создание SupplierDTO из supplier
     *
     * @param supplier - исходный supplier
     * @return - полученный SupplierDTO
     */
    public SupplierDTO createDTOFromSupplier(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(supplier.getSupplierId());
        supplierDTO.setCompanyName(supplier.getCompanyName());
        supplierDTO.setPhone(supplier.getPhone());
        return supplierDTO;
    }


    /**
     * Создание supplier из SupplierDTO
     *
     * @param supplierDTO - исходный supplier
     * @return - полученный SupplierDTO
     */
    public Supplier createSupplierFromDTO(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierDTO.getSupplierId());
        supplier.setCompanyName(supplierDTO.getCompanyName());
        supplier.setPhone(supplierDTO.getPhone());
        return supplier;
    }


}
