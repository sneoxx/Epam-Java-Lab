package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.converter.ProductDTOFromProductConverter;
import com.zaraev.epam.javacourses.converter.ProductFromProductDTOConverter;
import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final ProductDTOFromProductConverter productDTOFromProductConverter;
    
    private final ProductFromProductDTOConverter productFromProductDTOConverter;

    /**
     * Получение товара по id переданного в запросе
     * @param id - id из запроса
     * @return - экземпляр ProductDTO
     */
    @Override
    public ProductDTO get(int id) {
        ProductDTO productDTOCheck = productDTOFromProductConverter.convert(productService.getProduct(id));
        log.info("get() - Получен product {}",productDTOCheck);
        return productDTOCheck;
    }

    /**
     * Получение всех заказов
     * @return - коллекция List productDTO
     */
    @Override
    public List<ProductDTO> getAll() {
        List<Product> productList = productService.getAllProduct();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add( productDTOFromProductConverter.convert(product));
        }
        log.info("getAll()- Получены все product");
        return productDTOList;
    }

    /**
     * Создание нового товара из переданного json в запросе
     * @param productDTO - экземпляр customerDTO для создания
     * @return - созданный productDTO
     */
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        ProductDTO productDTOCheck = productDTOFromProductConverter.convert(productService.create(productFromProductDTOConverter.convert(productDTO)));
        log.info("create() - Создан новый product {}", productDTOCheck);
        return productDTOCheck;
    }

    /**
     * Обновление полей продукта с определенным id из запроса по данным переданным в json запроса
     * @param id - id продукта для обновления
     * @param productDTO  - productDTO полученный из json запроса
     * @return  - обновленный productDTO
     */
    @Override
    public ProductDTO update(int id, ProductDTO productDTO) {
        ProductDTO productDTOCheck = productDTOFromProductConverter.convert(productService.update(id, productFromProductDTOConverter.convert(productDTO)));
        log.info("update() - Обновлен product: {}", productDTOCheck);
        return productDTOCheck;
    }

    /**
     * Удаление продукта по id переданного в запросе
     * @param id - id удаляемого объекта
     * @return - удаленный объект
     */
    @Override
    public ProductDTO delete(int id) {
        ProductDTO productDTO = productDTOFromProductConverter.convert(productService.deleteById(id));
        log.info("delete() - Удален product {}", productDTO);
        return  productDTO;
    }
}