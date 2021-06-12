package com.zaraev.epam.javacourses.converter;


import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOFromProductConverter implements Converter<Product, ProductDTO> {

    @Override
    public ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setDiscountinued(product.isDiscountinued());
        productDTO.setUnitPrice(product.getUnitPrice());
        productDTO.setSupplierId(product.getSupplier().getSupplierId());
        return productDTO;
    }
}