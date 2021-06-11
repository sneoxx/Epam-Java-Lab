package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс для обработки веб запросов к Product
 */
@RestController
@Slf4j
public class ProductResourceImpl implements ProductResource {

    @Autowired
    private ProductService productService;

    /**
     * Получение товара по id переданного в запросе
     */
    @Override
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ProductDTO get(@PathVariable("id") int id) {
        log.info("get() - Получен product по id {}", id);
        return productService.getProduct(id);
    }

    /**
     * Получение всех товаров
     */
    @Override
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<ProductDTO> getAll() {
        log.info("getAll()- Получены все product");
        return productService.getAllProduct();
    }

    /**
     * Создаем нового товара из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        log.info("create() - Создан новый product {}", productDTO);
        return productService.create(productDTO);
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ProductDTO update(@PathVariable("id") int id, @RequestBody ProductDTO productDTO) {
        log.info("update() - Обновлен product c id {}", id);
        return productService.update(id, productDTO);
    }

    /**
     * Удаление товара по id переданного в запросе
     */
    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    @Override
    public ProductDTO delete(@PathVariable("id") int id) {
        log.info("delete() - Удален customer с id {}", id);
        return productService.deleteById(id);
    }
}