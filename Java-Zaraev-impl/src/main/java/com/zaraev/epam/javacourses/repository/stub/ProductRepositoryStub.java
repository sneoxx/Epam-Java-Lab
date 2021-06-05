package com.zaraev.epam.javacourses.repository.stub;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.helper.RepositoryHelper;
import com.zaraev.epam.javacourses.repository.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;

@Profile("local")
@Component
@Slf4j
public class ProductRepositoryStub implements IProductRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private RepositoryHelper repositoryHelper;

    /**
     * Запись в БД екземпляра Product - Ничего не сделает
     *
     * @param product - экземпляр supplier для занесения в Product
     * @return - вернет застабленный экземпляр Product
     */
    @Override
    public Product create(Product product) {
        var stub = repositoryHelper.product();
        log.info("create() Объект product застаблен {}", stub);
        return stub;
    }

    /**
     * Изменение в БД экземпляра product - Ничего не сделает
     *
     * @param product - экземпляр product, который необходимо изменить
     */
    @Override
    public void update(Product product) {
        log.info("update() Объект product застаблен");
        return;
    }

    /**
     * Получение из БД объекта Product - Ничего не сделает
     *
     * @param id - id объекта Product который необходимо получить
     * @return - вернет застабленный экземпляр Product
     */
    @Override
    public Product getProduct(int id) {
        var stub = repositoryHelper.product();
        log.info("getProduct() Объект product застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД объекта Product по полю orderNumber Product - Ничего не сделает
     *
     * @param productName - id объекта Order который необходимо получить
     * @return - вернет застабленный экземпляр Product
     */
    @Override
    public Product getProductWithInstance(String productName) {
        var stub = repositoryHelper.product();
        log.info("getProductWithInstance() Объект product застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД всех объектов Product - Ничего не сделает
     *
     * @return - Коллекция List из застабленного Product
     */
    @Override
    public List<Product> getAllProduct() {
        var stub = repositoryHelper.product();
        Product[] products = {stub};
        List<Product> productListList = Arrays.asList(products);
        log.info("getAllCustomer() Объект customer застаблен {}", productListList);
        return productListList;
    }

    /**
     * Удаление объекта product из БД - Ничего не сделает
     *
     * @param product - удаляемый объект
     */
    @Override
    public void deleteProduct(Product product) {
        var stub = repositoryHelper.product();
        log.info(" deleteProduct() Объект product застаблен ");
        return;
    }

    /**
     * Удаление объекта Product из БД по id - Ничего не сделает
     *
     * @param id- id удаляемого Product
     */
    @Override
    public void deleteProductWithId(int id) {
        log.info("deleteProductWithId() Объект product застаблен");
        return;
    }

}