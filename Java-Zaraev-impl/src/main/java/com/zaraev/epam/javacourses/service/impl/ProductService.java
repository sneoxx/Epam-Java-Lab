package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.bufferdata.BufferDataProduct;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Random;

@Slf4j
public class ProductService {

    ProductRepository productRepository = new ProductRepository();

    /**
     * Создание случайного supplier и передача на запись в БД
     *
     * @param supplier - экземпляр supplier
     * @return - экземпляр product
     */
    public Product createRandomProduct(Supplier supplier) {
        Product product = new Product();
        product.setProductName(generateRandomWord());
        product.setDiscountinued(true);
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setSupplier(supplier);
        productRepository.create(product);
        return product;
    }

    /**
     * Создание и передача на запись в БД екземпляра product и передача на запись в БД на основании объекта BufferDataProduct
     *
     * @param bufferDataProduct - Экземпляр bufferDataProduct
     * @return - экземпляр product
     */
    public Product create(BufferDataProduct bufferDataProduct) {
        Product product = new Product();
        product.setProductName(bufferDataProduct.getProductName());
        product.setDiscountinued(bufferDataProduct.isDiscountinued());
        product.setUnitPrice(bufferDataProduct.getUnitPrice());
        product.setSupplier(bufferDataProduct.getSupplier());
        productRepository.create(product);
        return product;
    }

    /**
     * Обновление екземпляра product и передача на запись в БД
     *
     * @param product - экземпляр product, на который необходимо изменить
     */
    public void update(Product product) {
        product.setProductName(product.getProductName() + "+" + generateRandomWord());
        productRepository.update(product);
    }

    /**
     * ООбновление екземпляра product и передача на запись в БД
     *
     * @param id      - id экземпляра product в базе, который необходимо изменить
     * @param product - экземпляр product, на который необходимо изменить
     */
    public void updateProductWithId(int id, Product product) {
        Product updateProduct = productRepository.getProduct(id);
        log.debug("updateProductWithId() Объект product передан на обновление: {} ", product);
        log.debug("updateProductWithId() Объект product передан на обновление: {} ", product);
        updateProduct.setProductName(product.getProductName());
        updateProduct.setUnitPrice(product.getUnitPrice());
        updateProduct.setDiscountinued(product.isDiscountinued());
        log.info("updateProductWithId() Объект product успешно обновлен: {} ", product);
        productRepository.update(updateProduct);
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
}