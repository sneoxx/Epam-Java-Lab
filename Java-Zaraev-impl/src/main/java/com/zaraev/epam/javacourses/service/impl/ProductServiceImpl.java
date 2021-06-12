package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Сервис для работы с ProductRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Создание случайного product и запись в БД
     *
     * @param supplier - экземпляр supplierDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    @Override
    public Product createRandomProduct(Supplier supplier) {
        Product product = new Product();
        product.setProductName(generateRandomWord());
        product.setDiscountinued(true);
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setSupplier(supplier);
        Product productCheck = productRepository.save(product);
        log.debug("createRandomProduct() Объект product успешно записан в БД: {} ", productCheck);
        return productCheck;
    }

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @param product - Экземпляр productDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    @Override
    public Product create(Product product) {
        Product productCheck =  productRepository.save(product);
        log.debug("create() Объект product успешно записан в БД: {} ", productCheck);
        return productCheck;
    }

    /**
     * Обновление случайными данными и запись в БД екземпляра Product
     *
     * @param product - экземпляр productDTO, на который необходимо изменить
     * @return - результат операции productDTO конвертированный из Product полученного из базы
     */
    @Override
    public Product updateRandomData(Product product) {
        product.setProductName(product.getProductName() + "+" + generateRandomWord());
        Product productCheck = productRepository.save(product);
        log.debug("updateRandomData() Объект order успешно обновлен в БД: {} ", productCheck);
        return productCheck;
    }

    /**
     * Обновление и запись в БД екземпляра product
     *
     * @param id         - id экземпляра product в базе, который необходимо изменить
     * @param product - экземпляр productDTO, на который необходимо изменить
     * @return - ProductDTO конвертированный из обновленного Product
     */
    @Override
    public Product update(int id, Product product) {
        Product updateProduct = productRepository.getOne(id);
        updateProduct.setProductName(product.getProductName());
        updateProduct.setUnitPrice(product.getUnitPrice());
        updateProduct.setDiscountinued(product.isDiscountinued());
        Product productCheck = productRepository.save(updateProduct);
        log.info("updateProductWithId() Объект product успешно обновлен: {} ", productCheck);
        return productCheck;
    }

    /**
     * Получение Product из базы
     *
     * @param id - id Product, которое необходимло получить
     * @return - ProductDTO созданный из полченного Customer
     */
    @Override
    public Product getProduct(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            log.debug("getProduct() Объект product успешно получен из БД: {}", product);
            return optionalProduct.get();
        }
        log.debug("getProduct() Объект product не найден, создан новый Product");
        return new Product();
    }

    /**
     * Получение всех Product из базы
     *
     * @return - коллекция ProductDTO конвертированная из полученной коллекции Product
     */
    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        log.debug("getAllProduct() Объекты product успешно получены из БД: {}", productList);
        return productList;
    }

    /**
     * Удаление Product из базы по id
     *
     * @param id - id Product для удаления
     * @return - ProductDTO конвертированный из удаленного Product
     */
    @Override
    public Product deleteById(int id) {
        Product productCheck = productRepository.getOne(id);
        productRepository.deleteById(id);
        log.debug("deleteById() Объект product успешно удален из БД: {}", productCheck);
        return productCheck;
    }

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

}