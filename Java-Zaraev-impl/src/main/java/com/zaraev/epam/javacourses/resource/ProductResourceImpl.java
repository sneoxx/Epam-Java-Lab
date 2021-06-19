package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.Logging;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки веб запросов к Product
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class ProductResourceImpl implements ProductResource {

    private final ProductService productService;

    private final ConversionService conversionService;

    /**
     * Получение товара по id переданного в запросе
     *
     * @param id - id из запроса
     * @return - экземпляр ProductDTO
     */
    @Logging
    @Override
    public ProductDTO get(int id) {
        Product productResult = productService.getProduct(id);
        ProductDTO productDTOCheck = conversionService.convert(productResult, ProductDTO.class);
        log.info("get() - Получен product {}", productDTOCheck);
        return productDTOCheck;
    }

    /**
     * Получение всех заказов
     *
     * @return - коллекция List productDTO
     */
    @Logging
    @Override
    public List<ProductDTO> getAll() {
        List<Product> productList = productService.getAllProduct();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(conversionService.convert(product, ProductDTO.class));
        }
        log.info("getAll()- Получены все product");
        return productDTOList;
    }

    /**
     * Создание нового товара из переданного json в запросе
     *
     * @param productDTO - экземпляр customerDTO для создания
     * @return - созданный productDTO
     */
    @Logging
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product productConvert = conversionService.convert(productDTO, Product.class);
        Product productResult = productService.create(productConvert);
        ProductDTO productDTOCheck = conversionService.convert(productResult, ProductDTO.class);
        log.info("create() - Создан новый product {}", productDTOCheck);
        return productDTOCheck;
    }

    /**
     * Обновление полей продукта с определенным id из запроса по данным переданным в json запроса
     *
     * @param id         - id продукта для обновления
     * @param productDTO - productDTO полученный из json запроса
     * @return - обновленный productDTO
     */
    @Logging
    @Override
    public ProductDTO update(int id, ProductDTO productDTO) {
        Product productConvert = conversionService.convert(productDTO, Product.class);
        Product productResult = productService.update(id, productConvert);
        ProductDTO productDTOCheck = conversionService.convert(productResult, ProductDTO.class);
        log.info("update() - Обновлен product: {}", productDTOCheck);
        return productDTOCheck;
    }

    /**
     * Удаление продукта по id переданного в запросе
     *
     * @param id - id удаляемого объекта
     * @return - удаленный объект ProductDTO
     */
    @Logging
    @Override
    public ProductDTO delete(int id) {
        Product productResult = productService.deleteById(id);
        ProductDTO productDTOCheck = conversionService.convert(productResult, ProductDTO.class);
        log.info("delete() - Удален product {}", productDTOCheck);
        return productDTOCheck;
    }
}