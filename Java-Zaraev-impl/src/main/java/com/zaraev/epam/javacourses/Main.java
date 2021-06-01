package com.zaraev.epam.javacourses;

import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationCustomer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {

    public static void main(String[] args) {

//        WorkDemonstrationCustomer workDemonstrationCustomer = new WorkDemonstrationCustomer();
//        WorkDemonstrationOrder workDemonstrationOrder = new WorkDemonstrationOrder();
//        WorkDemonstrationProduct workDemonstrationProduct = new WorkDemonstrationProduct();
//        WorkDemonstrationSupplier workDemonstrationSupplier = new WorkDemonstrationSupplier();
//        WorkDemonstrationOrderProduct workDemonstrationOrderProduct = new WorkDemonstrationOrderProduct();

        //    тест1
//        ApplicationContext context = new AnnotationConfigApplicationContext("com.zaraev.epam.javacourses"); // 1 создаем ApplicationContext
//        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
//        Customer customer = context.getBean(Customer.class);
//        customer.setPhone("111");
//        customer.setCustomerName("sdsd"); //менять имя
//        System.out.println(customer);
//        customerRepository.create(customer);
//        List<Customer> list =  customerRepository.getAllCustomer();
//        System.out.println(list);

//        // тест2
//        ApplicationContext context = new AnnotationConfigApplicationContext("com.zaraev.epam.javacourses");
//        CustomerService customerService = context.getBean(CustomerService.class);
//        Customer customer1 = customerService.createRandomCustomer();
//        System.out.println(customer1);

//        // тест3
        ApplicationContext context = new AnnotationConfigApplicationContext("com.zaraev.epam.javacourses");
        //     ApplicationContext context = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
        WorkDemonstrationCustomer workDemonstrationCustomer = context.getBean(WorkDemonstrationCustomer.class);
        // WorkDemonstrationProduct workDemonstrationProduct = context.getBean(WorkDemonstrationProduct.class);
////        WorkDemonstrationProduct workDemonstrationProduct = context.getBean(WorkDemonstrationProduct.class);
//        WorkDemonstrationSupplier workDemonstrationSupplier = context.getBean(WorkDemonstrationSupplier.class);
//        WorkDemonstrationOrder workDemonstrationOrder = context.getBean(WorkDemonstrationOrder.class);
//        WorkDemonstrationOrderProduct workDemonstrationOrderProduct = context.getBean(WorkDemonstrationOrderProduct.class);
        //  EService workDemonstrationOrderProduct = context.getBean(EService.class);
//        workDemonstrationCustomer.testCustomer();
//        workDemonstrationSupplier.testSupplier();
//        workDemonstrationProduct.testProduct();
//        workDemonstrationOrder.testOrder();
//        workDemonstrationOrderProduct.testSetOrderAndProduct();


        //тест4
        //   ApplicationContext context = new AnnotationConfigApplicationContext("com.zaraev.epam.javacourses");






        // сюда вставляем значения переменных для сообщения, которые подставляются за место {0} в бандле, указываем через запятую если их несколько
        // Locale.ENGLISH указываем локаоль для вывода соответсвующего бандла



//         customer = context.getBean("customer", Customer.class);
//        log.info("Customer: customerId - {}, customerName - {}, phone - {};", cust.getCustomerId(),
//                cust.getCustomerName(), cust.getPhone());
//
//         supplier = context.getBean("order", Order.class);
//        log.info("Order: orderId - {}, orderNumber - {}, totalAmount - {}, orderDate - {};", order.getOrderId(),
//                order.getOrderNumber(), order.getTotalAmount(), order.getOrderDate());
//
//         product = context.getBean("supplier", Supplier.class);
//        log.info("Supplier: supplierId - {}, companyName - {}, Phone - {}", sup.getSupplierId(), sup.getCompanyName(),
//                sup.getPhone());
//
//         order = context.getBean("product", Product.class);
//        log.info("Product: productId - {}, ProductName - {}, UnitPrice - {}", prod.getProductId(),
//                prod.getProductName(), prod.getUnitPrice());


//        log.info("{}", "Демонстрация MessageSource:");
//        MessageSource messageSource = (MessageSource) context.getBean("messageSource");
//        String message = messageSource.getMessage("message", null, "default msg", new Locale("ru", "RU"));
//        String message2 = messageSource.getMessage("message2", new Object[] { "test" }, "default msg", Locale.ENGLISH);  // new Object[] { "test" }
//        // сюда вставляем значения переменных для сообщения, которые подставляются за место {0} в бандле, указываем через запятую если их несколько
//        // Locale.ENGLISH указываем локаоль для вывода соответсвующего бандла
//        log.info("{}", message);
//        log.info("{}", message2);

//        log.info("{}", "Демонстрация решения циклической зависимости с помощью аннотации Lazy:");
//        SecondServiceExample secondServiceExample = context.getBean(SecondServiceExample.class); // получаем бин из контекста
//        log.info(secondServiceExample.getMsg()); // вызваем у бина метод
//
//        log.info("{}", "Демонстрация профилей спринга test и local:");
//        // spring.profiles.active=test,local в Environment variables конфигурации запуска
//        IService beanService = (IService) context.getBean("beanService"); // получаем из контекста beanService - это метод из LocalBeanConfig, приводим результат к IService
//        log.info(beanService.getMsg()); //вызваем у бина метод и получаем сообщение local bean service
//
//        // Если включен test профиль, то конфигурация не сработает
//        log.info("{}", "Демонстрация MessageSource:");
//        MessageSource messageSource = (MessageSource) context.getBean("messageSource");
//        String message = messageSource.getMessage("message", null, "default msg", new Locale("ru", "RU"));
//        String message2 = messageSource.getMessage("message2", new Object[] { "test" }, "default msg", Locale.ENGLISH);  // new Object[] { "test" }
//        // сюда вставляем значения переменных для сообщения, которые подставляются за место {0} в бандле, указываем через запятую если их несколько
//        // Locale.ENGLISH указываем локаоль для вывода соответсвующего бандла
//        log.info("{}", message);
//        log.info("{}", message2);
//
//
//        LessonExampleImpl example = context.getBean(LessonExampleImpl.class); //можем достать по классу или по имени
//        example.create();


//        Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//
//        CustomerService customerService = new CustomerService();
//        OrderService orderService = new OrderService();
//        ProductService productService = new ProductService();
//        SupplierService supplierService = new SupplierService();
//        ServletsHelper servletsHelper = new ServletsHelper();
//        CustomerRepository customerRepository = new CustomerRepository();
//        OrderRepository orderRepository = new OrderRepository();
//        ProductRepository productRepository = new ProductRepository();
//        SupplierRepository supplierRepository = new SupplierRepository();
//
//        String myJson = "{\n" +
//                "\"orderId\": 1,\n" +
//                "\"orderNumber\": \"10944+vkq\",\n" +
//                "\"orderDate\": \"May 28, 2021, 3:56:18 PM\",\n" +
//                "\"totalAmount\": 999.00,\n" +
//                "\"customer\":{\n" +
//                "\"customerId\": 3,\n" +
//                "\"customerName\": \"qglx\",\n" +
//                "\"phone\": \"7445\"\n" +
//                "},\n" +
//                "\"products\":[]\n" +
//                "}";
//
//        String myJson2 = "{\n" +
//                "\"customerId\": 3,\n" +
//                "\"customerName\": \"qglx\",\n" +
//                "\"phone\": \"7445\"\n" +
//                "}";
//
//
//        String myJson3 = "{\n" +
//                "\"supplierId\": 1,\n" +
//                "\"companyName\": \"yif\",\n" +
//                "\"phone\": \"98\"\n" +
//                "}";
//
//        String myJson4 = "{\n" +
//                "\"productId\": 1,\n" +
//                "\"productName\": \"nwoj+yxz\",\n" +
//                "\"unitPrice\": 100.00,\n" +
//                "\"isDiscountinued\": true,\n" +
//                "\"supplier\":{\"{\\n\" +\n" +
//                "                \"\\\"supplierId\\\": 1,\\n\" +\n" +
//                "                \"\\\"companyName\\\": \\\"yif\\\",\\n\" +\n" +
//                "                \"\\\"phone\\\": \\\"98\\\"\\n\" +\n" +
//                "                \"}\"},\n" +
//                "\"orders\":[\n" +
//                "{\n" +
//                "\"orderId\": 1,\n" +
//                "\"orderNumber\": \"4693+gxox\",\n" +
//                "\"orderDate\": \"May 29, 2021, 12:57:44 PM\",\n" +
//                "\"totalAmount\": 100.00,\n" +
//                "\"customer\":{\"customerId\": 3, \"customerName\": \"qglx\", \"phone\": \"7445\"}\n" +
//                "}\n" +
//                "]\n" +
//                "},";
//
//
//        String myJson5 = "{\n" +
//                "\"orderId\": 1,\n" +
//                "\"orderNumber\": \"lhjjjk\",\n" +
//                "\"orderDate\": \"May 29, 2021, 6:09:51 PM\",\n" +
//                "\"totalAmount\": 100.00,\n" +
//                "\"customer\":{\n" +
//                "\"customerId\": 3,\n" +
//                "\"customerName\": \"owr\",\n" +
//                "\"phone\": \"2086\"\n" +
//                "},\n" +
//                "\"products\":[\n" +
//                "{\n" +
//                "\"productId\": 5,\n" +
//                "\"productName\": \"hjyxz\",\n" +
//                "\"unitPrice\": 100.00,\n" +
//                "\"isDiscountinued\": true,\n" +
//                "\"supplier\":{\"supplierId\": 1, \"companyName\": \"xmlv\", \"phone\": \"6100\"}\n" +
//                "},\n" +
//                "{\n" +
//                "\"productId\": 1,\n" +
//                "\"productName\": \"kayg+nkk\",\n" +
//                "\"unitPrice\": 100.00,\n" +
//                "\"isDiscountinued\": true,\n" +
//                "\"supplier\":{\"supplierId\": 1, \"companyName\": \"xmlv\", \"phone\": \"6100\"}\n" +
//                "}\n" +
//                "]\n" +
//                "}";
//
//        String myJson6 = "{\n" +
//                "\"orderId\": 1,\n" +
//                "\"orderNumber\": \"27+mck\",\n" +
//                "\"orderDate\": \"May 29, 2021, 6:09:51 PM\",\n" +
//                "\"totalAmount\": 100.00,\n" +
//                "\"customer\":{\n" +
//                "\"customerId\": 3,\n" +
//                "\"customerName\": \"owr\",\n" +
//                "\"phone\": \"2086\"\n" +
//                "},\n" +
//                "\"products\":[\n" +
//                "{\n" +
//                "\"productId\": 1\n" +
//                "},\n" +
//                "{\n" +
//                "\"productId\": 5\n" +
//                "}\n" +
//                "]\n" +
//                "}";
//
//        String myJson7 = "{\n" +
//                "\"productId\": 3,\n" +
//                "\"productName\": \"ohd+ygn\",\n" +
//                "\"unitPrice\": 100.00,\n" +
//                "\"isDiscountinued\": true,\n" +
//                "\"supplier\":{\n" +
//                "\"supplierId\": 5,\n" +
//                "\"companyName\": \"okv\",\n" +
//                "\"phone\": \"6122\"\n" +
//                "}\n" +
//                "}";
//
//
//
//
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WER");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        // var stringFromJson = SERVICE_SERVLETS.parseJsonToString(req);
//        System.out.println(myJson7);
//
//        ProductDTO productDTO = GSON.fromJson(myJson7, ProductDTO.class);
//        System.out.println(productDTO);
        //  Product product = productService.create(productDTO);


//        OrderDTO orderDTO = GSON.fromJson(myJson6, OrderDTO.class);
//       // BufferDataOrder bufferDataOrder = GSON.fromJson(myJson6, BufferDataOrder.class);
//        System.out.println(orderDTO);
//        Order order = new Order();
//        order.setOrderNumber(orderDTO.getOrderNumber());
//        order.setOrderDate(orderDTO.getOrderDate());
//        order.setTotalAmount(orderDTO.getTotalAmount());
//        order.setCustomer(customerRepository.getCustomer(orderDTO.getCustomerId()));
//        order.setOrderId(null);
//        Set<Product> products = new HashSet<>();
//        for (Integer product : orderDTO.getProducts()) {
//            Product foundProduct = productRepository.getProduct(product);
//            products.add(foundProduct);
//        }
//
//                order.setProducts(products);
//        System.out.println(order);

//        Set<Product> tempProducts = customerDTO.getProducts();
//
//        Set<Product> products = new HashSet<>();
//
//        for (Product product : tempProducts) {
//            Product product1 = entityManager.find(Product.class, product.getProductId());
//            products.add(product1);
        //           System.out.println(product1);  //выведет с новой строки - тут не ошибится ввести string - итератор
    }

    //    bufferDataOrder.getProducts().forEach(productId -> {
    //       Product product = entityManager.find(Product.class, productId);
    //      Optional<Product> optionalProduct = Optional.ofNullable(entityManager.find(Product.class, productId));


//            public Optional<EntityDB> findById(Class clas, Integer id) {
//                EntityManager em = entityManager.createEntityManager();
//                EntityTransaction transaction = em.getTransaction();
//                try {
//                    transaction.begin();
//                    EntityDB result = (EntityDB) em.find(clas, id);
//                    transaction.commit();
//                    return Optional.ofNullable(result);
//                } catch (Exception e) {
//                    log.error("Ошибка: {}", e.getCause(), e);
//                    return Optional.empty();
//                } finally {
//                    em.close();
//                }
//            }


//            order = entityManager.find(Order.class, id);
//            if (optionalProduct.isPresent()) {
//                Product product = (Product) optionalProduct.get();
//                products.add(product);
//            }
    //       });
//        order.setProducts(products);
//        System.out.println(order);
//            Set<Product> products = new HashSet<>();
//            order.getProducts().forEach(productId -> {
//                Optional<Product> optionalProduct = Optional.ofNullable(entityManager.find(Product.class, productId));
//                if (optionalProduct.isPresent()) {
//                    Product product = (Product) optionalProduct.get();
//                    products.add(product);
//                }
//            });
//            order.setProducts(products);
//            transaction.begin();
//            entityManager.persist(order);
//            transaction.commit();
//            entityManager.close();
//            log.info("createOrderWithInstance() Объект Order создан и занесен в БД: {}", order);
//            return order;
//        } finally {
//            entityManager.close();
//        }
//    }

//        Order order = GSON.fromJson(myJson, Order.class);
//        System.out.println(order);

//      Supplier supplier = GSON.fromJson(myJson3, Supplier.class);
//      System.out.println(myJson3);
//      System.out.println(supplier);


}
