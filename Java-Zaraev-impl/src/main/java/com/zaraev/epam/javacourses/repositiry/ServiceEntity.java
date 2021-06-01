package com.zaraev.epam.javacourses.repositiry;

import com.zaraev.epam.javacourses.bufferdata.BufferDataOrder;
import com.zaraev.epam.javacourses.domain.entity.Customer;
import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
public class ServiceEntity {
    public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WER");
    static int orderNumber;

    /**
     * Создание и занесение в БД екземпляра Customer
     *
     * @return вернет занесенный экземпляр Customer
     */
    public Customer createCustomer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Customer customer = new Customer();
            customer.setCustomerName(generateRandomWord());
            customer.setPhone(getRandomNumber());
            transaction.begin();
            entityManager.persist(customer);
            transaction.commit();
            entityManager.close();
            log.info("createCustomer() Объект Customer создан и занесен в БД: {}", customer);
            return customer;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Создание и занесение в БД екземпляра Customer на основании объекта customer
     *
     * @param customer - объект customer
     * @return вернет занесенный экземпляр Customer
     */
    public Customer createCustomerWithInstance(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            customer.setCustomerId(null);
            transaction.begin();
            entityManager.persist(customer);
            transaction.commit();
            entityManager.close();
            log.info("createCustomerWithInstance() Объект Customer создан и занесен в БД: {}", customer);
            return customer;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Создание и занесение в БД екземпляра Order
     *
     * @param customer - экземпляр customer для занесения в Order
     * @return - вернет занесенный экземпляр Order
     */
    public Order createOrder(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerId());
            Order order = new Order();
            order.setOrderNumber(getRandomNumber());
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setTotalAmount(BigDecimal.valueOf(100 + orderNumber));
            order.setCustomer(tempCustomer);
            transaction.begin();
            entityManager.persist(order);
            transaction.commit();
            entityManager.close();
            log.info("createOrder() Объект Order создан и занесен в БД: {}", customer);
            return order;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Создание и занесение в БД екземпляра Order на основании объекта BufferDataOrder c проверкой наличия в базе
     * @param bufferDataOrder - Экземпляр BufferDataOrder
     * @return - экземпляр order
     */
    public Order createOrderWithInstanceBuf(BufferDataOrder bufferDataOrder) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Order order = new Order();
            order.setOrderId(null);
            order.setOrderNumber(bufferDataOrder.getOrderNumber());
            order.setOrderDate(bufferDataOrder.getOrderDate());
            order.setTotalAmount(bufferDataOrder.getTotalAmount());
            Customer tempCustomer = bufferDataOrder.getCustomer();
            order.setCustomer(entityManager.find(Customer.class, tempCustomer.getCustomerId()));
            Set<Product> products = new HashSet<>();
            Set<Product> tempProducts = bufferDataOrder.getProducts();
            for (Product product : tempProducts) {
                Product foundProduct = entityManager.find(Product.class, product.getProductId());
                products.add(foundProduct);
            }
            order.setProducts(products);
            transaction.begin();
            entityManager.persist(order);
            transaction.commit();
            entityManager.close();
            log.info("createOrderWithInstance() Объект Order создан и занесен в БД: {}", order);
            return order;
        } finally {
            entityManager.close();
        }
    }

     /**
     * Создание и занесение в БД екземпляра Supplier
     *
     * @return вернет занесенный экземпляр Supplier
     */
    public Supplier createSupplier() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Supplier supplier = new Supplier();
            supplier.setCompanyName(generateRandomWord());
            supplier.setPhone(getRandomNumber());
            transaction.begin();
            entityManager.persist(supplier);
            transaction.commit();
            entityManager.close();
            log.info("createSupplier() Объект Supplier создан и занесен в БД: {}", supplier);
            return supplier;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Создание и занесение в БД екземпляра Supplier на основании объекта supplier
     *
     * @param supplier - объект supplier
     * @return - вернет занесенный в БД объект Supplier
     */
    public Supplier createSupplierWithInstance(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            supplier.setSupplierId(null);
            transaction.begin();
            entityManager.persist(supplier);
            transaction.commit();
            entityManager.close();
            log.info("createCustomer() Объект Supplier создан и занесен в БД: {}", supplier);
            return supplier;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Создание и занесение в БД екземпляра Product
     *
     * @param supplier - экземпляр supplier для занесения в Product
     * @return - вернет занесенный экземпляр Product
     */
    public Product createProduct(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Supplier tempSupplier = entityManager.find(Supplier.class, supplier.getSupplierId());
            Product product = new Product();
            product.setProductName(generateRandomWord());
            product.setDiscountinued(true);
            product.setUnitPrice(BigDecimal.valueOf(100 + orderNumber));
            product.setSupplier(tempSupplier);
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
            entityManager.close();
            log.info("createProduct() Объект Product создан и занесен в БД: {}", product);
            return product;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Создание и занесение в БД екземпляра Product на основании объекта product
     *
     * @param product - объект product
     * @return - вернет занесенный в БД объект Product
     */
    public Product createProductWithInstance(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            product.setProductId(null);
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
            entityManager.close();
            log.info("createProductWithInstance Объект Product создан и занесен в БД: {}", product);
            return product;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Создание связи между Order Product
     *
     * @param order   - соединяемый order
     * @param product - соединяемый product
     */
    public void createBondOrderAndProduct(Order order, Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Order tempOrder = entityManager.find(Order.class, order.getOrderId());
            Product tempProduct = entityManager.find(Product.class, product.getProductId());
            transaction.begin();
            tempOrder.getProducts().add(tempProduct);
            tempProduct.getOrders().add(tempOrder);
            entityManager.persist(tempOrder);
            entityManager.persist(tempProduct);
            transaction.commit();
            entityManager.close();
            log.info("createBondOrderAndProduct() Создана связь многие ко многим у объектов {} и {}:", order, product);
        } catch (Exception e) {
            log.error("createBondOrderAndProduct() Ошибка создания связи многие ко многим: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра customer
     *
     * @param customer - экземпляр customer, который необходимо изменить
     */
    public void updateCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            log.debug("updateCustomer() Объект сustomer передан на обновление: {} ", customer);
            customer.setCustomerName(customer.getCustomerName() + "+" + generateRandomWord());
            transaction.begin();
            entityManager.merge(customer);
            transaction.commit();
            entityManager.close();
            log.info("updateCustomer() Объект сustomer успешно обновлен: {} ", customer);
        } catch (Exception e) {
            log.error("updateCustomer() Ошибка обновления объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра customer
     *
     * @param id       - id экземпляра customer в базе, который необходимо изменить
     * @param customer - экземпляр customer, который необходимо изменить
     */
    public void updateCustomerWithId(int id, Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Customer updateCustomer = entityManager.find(Customer.class, id);
            log.debug("updateCustomerWithId() Объект customer передан на обновление: {} ", customer);
            updateCustomer.setCustomerName(customer.getCustomerName());
            updateCustomer.setPhone(customer.getPhone());
            transaction.begin();
            entityManager.merge(updateCustomer);
            transaction.commit();
            log.info("updateCustomerWithId() Объект customer успешно обновлен: {} ", customer);
            entityManager.close();
        } catch (Exception e) {
            log.error("updateCustomerWithId() Ошибка обновления объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра order
     *
     * @param order - экземпляр order, который необходимо изменить
     */
    public void updateOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            log.debug("updateCustomer() Объект order передан на обновление: {} ", order);
            order.setOrderNumber(order.getOrderNumber() + "+" + generateRandomWord());
            transaction.begin();
            entityManager.merge(order);
            transaction.commit();
            entityManager.close();
            log.info("updateOrder() Объект order успешно обновлен: {} ", order);
        } catch (Exception e) {
            log.error("updateOrder() Ошибка обновления объекта order: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра order
     *
     * @param id    - id экземпляра order в базе, который необходимо изменить
     * @param order - экземпляр order, который необходимо изменить
     */
    public void updateOrderWithId(int id, Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Order updateOrder = entityManager.find(Order.class, id);
            log.debug("updateProductWithId() Объект order передан на обновление: {} ", order);
            updateOrder.setOrderNumber(order.getOrderNumber());
            updateOrder.setOrderDate(order.getOrderDate());
            updateOrder.setTotalAmount(order.getTotalAmount());
            transaction.begin();
            entityManager.merge(updateOrder);
            transaction.commit();
            log.info("updateProductWithId() Объект order успешно обновлен: {} ", order);
            entityManager.close();
        } catch (Exception e) {
            log.error("updateProductWithId() Ошибка обновления объекта order: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра supplier
     *
     * @param supplier - экземпляр supplier, который необходимо изменить
     */
    public void updateSupplier(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            log.debug("updateSupplier() Объект supplier передан на обновление: {} ", supplier);
            supplier.setCompanyName(supplier.getCompanyName() + "+" + generateRandomWord());
            transaction.begin();
            entityManager.merge(supplier);
            transaction.commit();
            log.info("updateSupplier() Объект supplier успешно обновлен: {} ", supplier);
            entityManager.close();
        } catch (Exception e) {
            log.error("updateSupplier() Ошибка обновления объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра supplier
     *
     * @param id       - id экземпляра supplier в базе, который необходимо изменить
     * @param supplier - экземпляр supplier, который необходимо изменить
     */
    public void updateSupplierWithId(int id, Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Supplier updateSupplier = entityManager.find(Supplier.class, id);
            log.debug("updateSupplier() Объект supplier передан на обновление: {} ", supplier);
            updateSupplier.setCompanyName(supplier.getCompanyName());
            updateSupplier.setPhone(supplier.getPhone());
            transaction.begin();
            entityManager.merge(updateSupplier);
            transaction.commit();
            log.info("updateSupplier() Объект supplier успешно обновлен: {} ", supplier);
            entityManager.close();
        } catch (Exception e) {
            log.error("updateSupplier() Ошибка обновления объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра product
     *
     * @param product - экземпляр product, который необходимо изменить
     */
    public void updateProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            log.debug("updateProduct() Объект product передан на обновление: {} ", product);
            product.setProductName(product.getProductName() + "+" + generateRandomWord());
            transaction.begin();
            entityManager.merge(product);
            transaction.commit();
            entityManager.close();
            log.info("updateProduct() Объект product успешно обновлен: {} ", product);
        } catch (Exception e) {
            log.error("updateProduct() Ошибка обновления объекта product: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра product
     *
     * @param id      - id экземпляра product в базе, который необходимо изменить
     * @param product - экземпляр product, который необходимо изменить
     */
    public void updateProductWithId(int id, Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Product updateProduct = entityManager.find(Product.class, id);
            log.debug("updateProductWithId() Объект product передан на обновление: {} ", product);
            updateProduct.setProductName(product.getProductName());
            updateProduct.setUnitPrice(product.getUnitPrice());
            updateProduct.setDiscountinued(product.isDiscountinued());
            transaction.begin();
            entityManager.merge(updateProduct);
            transaction.commit();
            log.info("updateProductWithId() Объект product успешно обновлен: {} ", product);
            entityManager.close();
        } catch (Exception e) {
            log.error("updateProductWithId() Ошибка обновления объекта product: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Получение из БД объекта Customer
     *
     * @param id - id объекта Customer который необходимо получить
     * @return - объект Customer из БД
     */
    public Customer getCustomer(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try {
            customer = entityManager.find(Customer.class, id);
            log.info("getCustomer() Объект customer успешно получен из БД {}", customer);
            entityManager.close();
            return customer;
        } catch (Exception e) {
            log.error("getCustomer() Ошибка получения из БД объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
        return customer;
    }

    /**
     * Получение из БД всех объектов Customer
     *
     * @return - Коллекция List всех объектов Customer из БД
     */
    public List<Customer> getAllCustomer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customerList = new ArrayList<>();
        try {
            String query = "SELECT c FROM Customer c";
            customerList = entityManager
                    .createQuery(query, Customer.class)
                    .getResultList();
            entityManager.close();
            log.info("getAllCustomer() Выведен список всех Customer: {}", customerList);
            return customerList;
        } catch (Exception e) {
            log.error("getAllCustomer() Ошибка получения из БД объектов сustomer: ", e);
        } finally {
            entityManager.close();
        }
        return customerList;
    }

    /**
     * Получение из БД объекта Order
     *
     * @param id - id объекта Order который необходимо получить
     * @return - объект Order из БД
     */
    public Order getOrder(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Order order = null;
        try {
            order = entityManager.find(Order.class, id);
            log.info("getOrder() Объект order успешно получен из БД {}", order);
            entityManager.close();
            return order;
        } catch (Exception e) {
            log.error("getOrder() Ошибка получения из БД объекта order: ", e);
        } finally {
            entityManager.close();
        }
        return order;
    }

    /**
     * Получение из БД всех объектов Order
     *
     * @return - Коллекция List всех объектов Order из БД
     */
    public List<Order> getAllOrder() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Order> orderList = new ArrayList<>();
        try {
            String query = "SELECT o FROM Order o";
            orderList = entityManager
                    .createQuery(query, Order.class)
                    .getResultList();
            entityManager.close();
            log.info("getAllOrder() Выведен список всех Order: {}", orderList);
            return orderList;
        } catch (Exception e) {
            log.error("getAllOrder() Ошибка получения из БД объектов order: ", e);
        } finally {
            entityManager.close();
        }
        return orderList;
    }

    /**
     * Получение из БД объекта Supplier
     *
     * @param id - id объекта Supplier который необходимо получить
     * @return - объект Supplier из БД
     */
    public Supplier getSupplier(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Supplier supplier = null;
        try {
            supplier = entityManager.find(Supplier.class, id);
            log.info("getSupplier() Объект supplier успешно получен из БД {}", supplier);
            entityManager.close();
            return supplier;
        } catch (Exception e) {
            log.error("getSupplier() Ошибка получения из БД объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
        return supplier;
    }

    /**
     * Получение из БД всех объектов Supplier
     *
     * @return - Коллекция List всех объектов Supplier из БД
     */
    public List<Supplier> getAllSupplier() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Supplier> supplierList = new ArrayList<>();
        try {
            String query = "SELECT s FROM Supplier s";
            supplierList = entityManager
                    .createQuery(query, Supplier.class)
                    .getResultList();
            entityManager.close();
            log.info("getAllSupplier() Выведен список всех Supplier: {}", supplierList);
            return supplierList;
        } catch (Exception e) {
            log.error("getAllSupplier() Ошибка получения из БД объектов supplier: ", e);
        } finally {
            entityManager.close();
        }
        return supplierList;
    }

    /**
     * Получение из БД объекта Product
     *
     * @param id - id объекта Product который необходимо получить
     * @return - объект Product из БД
     */
    public Product getProduct(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = null;
        try {
            product = entityManager.find(Product.class, id);
            log.info("getProduct() Объект product успешно получен из БД {}", product);
            entityManager.close();
            return product;
        } catch (Exception e) {
            log.error("getProduct() Ошибка получения из БД объекта product: ", e);
        } finally {
            entityManager.close();
        }
        return product;
    }

    /**
     * Получение из БД всех объектов Product
     *
     * @return - Коллекция List всех объектов Product из БД
     */
    public List<Product> getAllProduct() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Product> productList = null;
        try {
            String query = "SELECT p FROM Product p";
            productList = entityManager
                    .createQuery(query, Product.class)
                    .getResultList();
            entityManager.close();
            log.info("getAllProduct() Выведен список всех Product: {}", productList);
            return productList;
        } catch (Exception e) {
            log.error("getAllProduct() Ошибка получения из БД объектов product: ", e);
        } finally {
            entityManager.close();
        }
        return productList;
    }

    /**
     * Удаление объекта customer из БД
     *
     * @param customer - удаляемый объект
     */
    public void deleteCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Customer tempCustomer = entityManager.find(Customer.class, customer.getCustomerId());
            transaction.begin();
            log.debug("deleteCustomer() Объект customer передан на удаление: {}", tempCustomer);
            if (entityManager.contains(tempCustomer)) {
                entityManager.remove(tempCustomer);
                transaction.commit();
                log.info("deleteCustomer() Объект customer успешно удален: {}", tempCustomer);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteCustomer() Ошибка удаления объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
    }

    public void deleteCustomerWithId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Customer tempCustomer = entityManager.find(Customer.class, id);
            transaction.begin();
            log.debug("deleteCustomer() Объект customer передан на удаление: {}", tempCustomer);
            if (entityManager.contains(tempCustomer)) {
                entityManager.remove(tempCustomer);
                transaction.commit();
                log.info("deleteCustomer() Объект customer успешно удален: {}", tempCustomer);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteCustomer() Ошибка удаления объекта сustomer: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Удаление объекта order из БД
     *
     * @param order - удаляемый объект
     */
    public void deleteOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Order tempOrder = entityManager.find(Order.class, order.getOrderId());
            transaction.begin();
            log.debug("deleteOrder() Объект order передан на удаление: {}", tempOrder);
            if (entityManager.contains(tempOrder)) {
                entityManager.remove(tempOrder);
                transaction.commit();
                log.info("deleteOrder() Объект order успешно удален: {}", tempOrder);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteOrder() Ошибка удаления объекта order: ", e);
        } finally {
            entityManager.close();
        }
    }

    public void deleteOrderWithId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Order tempOrder = entityManager.find(Order.class, id);
            transaction.begin();
            log.debug("deleteOrderWithId() Объект order передан на удаление: {}", tempOrder);
            if (entityManager.contains(tempOrder)) {
                entityManager.remove(tempOrder);
                transaction.commit();
                log.info("deleteOrderWithId() Объект order успешно удален: {}", tempOrder);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteOrderWithId() Ошибка удаления объекта order: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Удаление объекта supplier из БД
     *
     * @param supplier - удаляемый объект
     */
    public void deleteSupplier(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Supplier tempSupplier = entityManager.find(Supplier.class, supplier.getSupplierId());
            transaction.begin();
            log.debug("deleteSupplier() Объект supplier передан на удаление: {}", tempSupplier);
            if (entityManager.contains(tempSupplier)) {
                entityManager.remove(tempSupplier);
                transaction.commit();
                log.info("deleteSupplier() Объект supplier успешно удален: {}", tempSupplier);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteSupplier() Ошибка удаления объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
    }

    public void deleteSupplierWithId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Supplier tempSupplier = entityManager.find(Supplier.class, id);
            transaction.begin();
            log.debug("deleteSupplier() Объект supplier передан на удаление: {}", tempSupplier);
            if (entityManager.contains(tempSupplier)) {
                entityManager.remove(tempSupplier);
                transaction.commit();
                log.info("deleteSupplier() Объект supplier успешно удален: {}", tempSupplier);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteSupplier() Ошибка удаления объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Удаление объекта product из БД
     *
     * @param product - удаляемый объект
     */
    public void deleteProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Product tempProduct = entityManager.find(Product.class, product.getProductId());
            transaction.begin();
            log.debug("deleteProduct() Объект supplier передан на удаление: {}", tempProduct);
            if (entityManager.contains(tempProduct)) {
                entityManager.remove(tempProduct);
                transaction.commit();
                log.info("deleteProduct() Объект supplier успешно удален: {}", tempProduct);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteProduct() Ошибка удаления объекта product: ", e);
        } finally {
            entityManager.close();
        }
    }

    public void deleteProductWithId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Product tempProduct = entityManager.find(Product.class, id);
            transaction.begin();
            log.debug("deleteProductWithId Объект product передан на удаление: {}", tempProduct);
            if (entityManager.contains(tempProduct)) {
                entityManager.remove(tempProduct);
                transaction.commit();
                log.info("deleteProductWithId Объект order успешно удален: {}", tempProduct);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteProductWithId() Ошибка удаления объекта product: ", e);
        } finally {
            entityManager.close();
        }
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