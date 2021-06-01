package com.zaraev.epam.javacourses.repositiry;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Slf4j
public class ProductRepository {

        public EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WER");
        static int orderNumber;

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
