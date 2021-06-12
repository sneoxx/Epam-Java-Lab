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
     */
    @Override
    public ProductDTO get(int id) {
        log.info("get() - Получен product по id {}", id);
        return productDTOFromProductConverter.convert(productService.getProduct(id));
    }

    /**
     * Получение всех товаров
     */
    @Override
    public List<ProductDTO> getAll() {
        log.info("getAll()- Получены все product");
        List<Product> productList = productService.getAllProduct();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add( productDTOFromProductConverter.convert(product));
        }
        return productDTOList;
    }

    /**
     * Создаем нового товара из переданного json в запросе
     */
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        log.info("create() - Создан новый product {}", productDTO);
        return  productDTOFromProductConverter.convert(productService.create(productFromProductDTOConverter.convert(productDTO)));
    }

    /**
     * Обновление полей товара из переданного json в запросе
     */
    @Override
    public ProductDTO update(int id, ProductDTO productDTO) {
        log.info("update() - Обновлен product c id {}", id);
        return  productDTOFromProductConverter.convert(productService.update(id, productFromProductDTOConverter.convert(productDTO)));
    }

    /**
     * Удаление товара по id переданного в запросе
     */
    @Override
    public ProductDTO delete(int id) {
        log.info("delete() - Удален customer с id {}", id);
        return  productDTOFromProductConverter.convert(productService.deleteById(id));
    }
}