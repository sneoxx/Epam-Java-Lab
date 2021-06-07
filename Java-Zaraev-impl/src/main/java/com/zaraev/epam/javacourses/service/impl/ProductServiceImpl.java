package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.helper.ServiceHelper;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import com.zaraev.epam.javacourses.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с ProductRepository
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    private final ServiceHelper serviceHelper = new ServiceHelper();

    /**
     * Создание случайного supplier и передача на запись в БД экзмепляра supplier
     *
     * @param supplier - экземпляр supplier
     * @return - экземпляр product
     */
    @Override
    public Product createRandomProduct(Supplier supplier) {
        Product product = new Product();
        product.setProductName(serviceHelper.generateRandomWord());
        product.setDiscountinued(true);
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setSupplier(supplier);
        return productRepository.create(product);
    }

    /**
     * Создание и передача на запись в БД екземпляра product и передача на запись в БД
     *
     * @param productDTO - Экземпляр productDTO
     * @return - результат опрерации orderDTO
     */
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setDiscountinued(productDTO.isDiscountinued());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setSupplier(supplierRepository.get(productDTO.getSupplierId()));
        productRepository.create(product);
        Product productCheck = productRepository.get(product.getProductId());
        return serviceHelper.createProductDTO(productCheck);
    }

    /**
     * Обновление екземпляра product и передача на запись в БД
     *
     * @param product - экземпляр product, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    @Override
    public ProductDTO updateRandomData(Product product) {
        product.setProductName(product.getProductName() + "+" + serviceHelper.generateRandomWord());
        productRepository.update(product);
        Product productCheck = productRepository.get(product.getProductId());
        return serviceHelper.createProductDTO(productCheck);
    }

    /**
     * Обновление екземпляра product и передача на запись в БД
     *
     * @param id          - id экземпляра product в базе, который необходимо изменить
     * @param productDTO- экземпляр productDTO, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    @Override
    public ProductDTO update(int id, ProductDTO productDTO) {
        Product updateProduct = productRepository.get(id);
        log.debug("updateProductWithId() Объект productDTO передан на обновление: {} ", productDTO);
        updateProduct.setProductName(productDTO.getProductName());
        updateProduct.setUnitPrice(productDTO.getUnitPrice());
        updateProduct.setDiscountinued(productDTO.isDiscountinued());
        log.info("updateProductWithId() Объект product успешно обновлен: {} ", updateProduct);
        productRepository.update(updateProduct);
        Product productCheck = productRepository.get(updateProduct.getProductId());
        return serviceHelper.createProductDTO(productCheck);
    }

    /**
     * Получение CustomerDTO из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полченного Customer
     */
    @Override
    public ProductDTO getProduct(int id) {
        Product product = productRepository.get(id);
        return serviceHelper.createProductDTO(product);
    }

    /**
     * Получение всех CustomerDTO из базы
     *
     * @return - CustomerDTO созданный из полученного Customer
     */
    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productRepository.getAllProduct();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(serviceHelper.createProductDTO(product));
        }
        return productDTOList;
    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     */
    @Override
    public void deleteProductWithId(int id) {
        productRepository.delete(id);
    }


}