package com.zaraev.epam.javacourses.converter;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертер из ProductDTO в Product
 */
@Component
public class ProductFromProductDTOConverter implements Converter<ProductDTO, Product> {

    @Override
    public Product convert(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setDiscountinued(productDTO.isDiscountinued());
        product.setUnitPrice(productDTO.getUnitPrice());
        System.out.println(productDTO.getSupplierId());
        Supplier supplier = new Supplier();
        supplier.setSupplierId(productDTO.getSupplierId());
        product.setSupplier(supplier);
        return product;
    }
}