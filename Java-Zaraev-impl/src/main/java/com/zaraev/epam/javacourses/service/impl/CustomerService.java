package com.zaraev.epam.javacourses.service.impl;


import com.zaraev.epam.javacourses.domain.entity.*;
import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import com.zaraev.epam.javacourses.service.EService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
@Slf4j
public class CustomerService implements EService {

    @Autowired
    private CustomerRepository customerRepository;// = new CustomerRepository();
    // public CustomerRepository customerRepository = new CustomerRepository();


//    @Autowired
//    public CustomerService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    /**
     * Создание случайного сustomer и передача на запись в БД
     *
     * @return - экземпляр customer
     */
    public Customer createRandomCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(generateRandomWord());
        customer.setPhone(getRandomNumber());
        customerRepository.create(customer);
        return customer;
    }

    public String abstractEntity(IEntity iEntity, Locale locale) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.zaraev.epam.javacourses");
        Customer customer = context.getBean(Customer.class);
        Supplier supplier = context.getBean(Supplier.class);
        Product product = context.getBean(Product.class);
        Order order = context.getBean(Order.class);
        log.info("{}", "Демонстрация хардкор значений local:");
        log.info("customer : {}", customer);
        log.info("supplier : {}", supplier);
        log.info("product : {}", product);
        log.info("order : {}", order);
        log.info("{}", "Демонстрация Бандла:");
        MessageSource messageSource = (MessageSource) context.getBean("messageSource");
        if (iEntity.getClass().equals("Customer.class")) {
            String customerRu = messageSource.getMessage("message", new Object[]{customer.getCustomerId(), customer.getCustomerName(), customer.getPhone()}, "Ошибка", new Locale("ru", "RU"));
        } else if(iEntity.getClass().equals("Customer.class")){
            String productEn = messageSource.getMessage("message3", new Object[]{product.getProductId(), product.getProductName(), product.getUnitPrice(), product.isDiscountinued()}, "Error", Locale.ENGLISH);
        } else if(iEntity.getClass().equals("Customer.class")){

        }


        String orderEn = messageSource.getMessage("message4", new Object[]{order.getOrderId(), order.getOrderNumber(),
                order.getOrderDate(), order.getTotalAmount(), order.getCustomer().getCustomerId(), order.getProducts()}, "Error", Locale.ENGLISH);
        log.info("{}", customerRu);
        log.info("{}", productEn);
        log.info("{}", orderEn);

    }


    /**
     * Создание и передача на запись в БД екземпляра customer
     *
     * @param customerDTO - Экземпляр customerDTO
     * @return - результат опрерации сustomerDTO полученный из базы
     */
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPhone(customerDTO.getPhone());
        customerRepository.create(customer);
        Customer customerCheck = customerRepository.getCustomerWithInstance(customer.getCustomerName());
        return createCustomerDTO(customerCheck);
    }


    /**
     * Обновление екземпляра customer и передача на обновление в БД
     *
     * @param customer - экземпляр customer, на который необходимо изменить
     * @return - результат опрерации сustomerDTO
     */
    public CustomerDTO update(Customer customer) {
        customer.setCustomerName(customer.getCustomerName() + "+" + generateRandomWord());
        customerRepository.update(customer);
        Customer customerCheck = customerRepository.getCustomerWithInstance(customer.getCustomerName());
        return createCustomerDTO(customerCheck);
    }

    /**
     * Обновление экземпляра customer и передача на обновление в БД
     *
     * @param id          - id экземпляра customer в базе, который необходимо изменить
     * @param customerDTO - экземпляр customer, на который необходимо изменить
     * @return - результат опрерации сustomerDTO
     */
    public CustomerDTO updateCustomerWithId(int id, CustomerDTO customerDTO) {
        Customer updatecustomer = customerRepository.getCustomer(id);
        log.debug("updateProductWithId() Объект customerDTO передан на обновление: {} ", customerDTO);
        updatecustomer.setCustomerName(customerDTO.getCustomerName());
        updatecustomer.setPhone(customerDTO.getPhone());
        log.info("updateProductWithId() Объект customer успешно обновлен: {} ", updatecustomer);
        customerRepository.update(updatecustomer);
        Customer customerCheck = customerRepository.getCustomerWithInstance(updatecustomer.getCustomerName());
        return createCustomerDTO(customerCheck);
    }

    /**
     * Получение CustomerDTO из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полченного Customer
     */
    public CustomerDTO getCustomer(int id) {
        Customer customer = customerRepository.getCustomer(id);
        return createCustomerDTO(customer);
    }

    /**
     * Получение всех CustomerDTO из базы
     *
     * @return - CustomerDTO созданный из полученного Customer
     */
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customerList = customerRepository.getAllCustomer();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDTOList.add(createCustomerDTO(customer));
        }
        return customerDTOList;
    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     */
    public void deleteCustomerWithId(int id) {
        customerRepository.deleteCustomerWithId(id);
    }

    /**
     * Создание сustomerDTO из customer
     *
     * @param customer - исходный supplier
     * @return - полученный сustomerDTO
     */
    public CustomerDTO createCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setCustomerId(customer.getCustomerId());
        return customerDTO;
    }


    /**
     * Генерация случайного слова
     *
     * @return - случайное слово
     */
    public static String generateRandomWord() {
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
}