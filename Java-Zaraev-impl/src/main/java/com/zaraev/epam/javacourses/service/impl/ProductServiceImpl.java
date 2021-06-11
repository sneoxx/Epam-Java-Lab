package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.dto.SupplierDTO;
import com.zaraev.epam.javacourses.helper.ServiceHelper;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import com.zaraev.epam.javacourses.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с ProductRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;


    private final ServiceHelper serviceHelper = new ServiceHelper();

    /**
     * Создание случайного product и запись в БД
     *
     * @param supplierDTO - экземпляр supplierDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    @Override
    public ProductDTO createRandomProduct(SupplierDTO supplierDTO) {
        Product product = new Product();
        product.setProductName(serviceHelper.generateRandomWord());
        product.setDiscountinued(true);
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setSupplier(serviceHelper.createSupplierFromDTO(supplierDTO));
        productRepository.saveAndFlush(product);
        Product productCheck = productRepository.getOne(product.getProductId());
        log.debug("createRandomProduct() Объект product успешно записан в БД: {} ", productCheck);
        return serviceHelper.createDTOFromProduct(productCheck);
    }

    /**
     * Создание и запись в БД рандомного Supplier
     *
     * @param productDTO - Экземпляр productDTO
     * @return - supplierDTO конвертированный из Supplier записанного в базу
     */
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setDiscountinued(productDTO.isDiscountinued());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setSupplier(supplierRepository.getOne(productDTO.getSupplierId()));
        productRepository.saveAndFlush(product);
        Product productCheck = productRepository.getOne(product.getProductId());
        log.debug("create() Объект product успешно записан в БД: {} ", productCheck);
        return serviceHelper.createDTOFromProduct(productCheck);
    }

    /**
     * Обновление случайными данными и запись в БД екземпляра Product
     *
     * @param productDTO - экземпляр productDTO, на который необходимо изменить
     * @return - результат операции productDTO конвертированный из Product полученного из базы
     */
    @Override
    public ProductDTO updateRandomData(ProductDTO productDTO) {
        productDTO.setProductName(productDTO.getProductName() + "+" + serviceHelper.generateRandomWord());
        productRepository.saveAndFlush(serviceHelper.createProductFromDTO(productDTO, supplierRepository));
        Product productCheck = productRepository.getOne(productDTO.getProductId());
        log.debug("updateRandomData() Объект order успешно обновлен в БД: {} ", productCheck);
        return serviceHelper.createDTOFromProduct(productCheck);
    }

    /**
     * Обновление и запись в БД екземпляра product
     *
     * @param id         - id экземпляра product в базе, который необходимо изменить
     * @param productDTO - экземпляр productDTO, на который необходимо изменить
     * @return - ProductDTO конвертированный из обновленного Product
     */
    @Override
    public ProductDTO update(int id, ProductDTO productDTO) {
        Product updateProduct = productRepository.getOne(id);
        updateProduct.setProductName(productDTO.getProductName());
        updateProduct.setUnitPrice(productDTO.getUnitPrice());
        updateProduct.setDiscountinued(productDTO.isDiscountinued());
        productRepository.saveAndFlush(updateProduct);
        Product productCheck = productRepository.getOne(updateProduct.getProductId());
        log.info("updateProductWithId() Объект product успешно обновлен: {} ", productCheck);
        return serviceHelper.createDTOFromProduct(productCheck);
    }

    /**
     * Получение Product из базы
     *
     * @param id - id Product, которое необходимло получить
     * @return - ProductDTO созданный из полченного Customer
     */
    @Override
    public ProductDTO getProduct(int id) {
        Product product = productRepository.getOne(id);
        log.debug("getProduct() Объект product успешно получен из БД");
        return serviceHelper.createDTOFromProduct(product);
    }

    /**
     * Получение всех Product из базы
     *
     * @return - коллекция ProductDTO конвертированная из полученной коллекции Product
     */
    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(serviceHelper.createDTOFromProduct(product));
        }
        log.debug("getAllProduct() Объекты product успешно получены из БД");
        return productDTOList;
    }

    /**
     * Удаление Product из базы по id
     *
     * @param id - id Product для удаления
     * @return - ProductDTO конвертированный из удаленного Product
     */
    @Override
    public ProductDTO deleteById(int id) {
        ProductDTO productDTO = serviceHelper.createDTOFromProduct(productRepository.getOne(id));
        productRepository.deleteById(id);
        log.debug("deleteById() Объект product успешно удален из БД");
        return productDTO;
    }
}