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
     * Создание случайного supplier и передача на запись в БД экзмепляра supplier
     *
     * @param supplierDTO - экземпляр supplier
     * @return - экземпляр product
     */
    @Override
    public ProductDTO createRandomProduct(SupplierDTO supplierDTO) {
        Product product = new Product();
        product.setProductName(serviceHelper.generateRandomWord());
        product.setDiscountinued(true);
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setSupplier(serviceHelper.createSupplierFromDTO(supplierDTO));
        return serviceHelper.createDTOFromProduct(productRepository.saveAndFlush(product));
    }

//    @Override
//    public ProductDTO createRandomProduct(SupplierDTO supplierDTO) {
//        Product product = new Product();
//        product.setProductName(serviceHelper.generateRandomWord());
//        product.setDiscountinued(true);
//        product.setUnitPrice(BigDecimal.valueOf(100));
//        product.setSupplier(serviceHelper.createSupplierFromDTO(supplierDTO));
//        return serviceHelper.createDTOFromProduct(productRepository.create(product));
//    }

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
        product.setSupplier(supplierRepository.getOne(productDTO.getSupplierId()));
        productRepository.saveAndFlush(product);
        Product productCheck = productRepository.getOne(product.getProductId());
        return serviceHelper.createDTOFromProduct(productCheck);
    }

//    @Override
//    public ProductDTO create(ProductDTO productDTO) {
//        Product product = new Product();
//        product.setProductName(productDTO.getProductName());
//        product.setDiscountinued(productDTO.isDiscountinued());
//        product.setUnitPrice(productDTO.getUnitPrice());
//        product.setSupplier(supplierRepository.get(productDTO.getSupplierId()));
//        productRepository.create(product);
//        Product productCheck = productRepository.get(product.getProductId());
//        return serviceHelper.createDTOFromProduct(productCheck);
//    }

    /**
     * Обновление екземпляра product и передача на запись в БД
     *
     * @param productDTO - экземпляр productDTO, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    @Override
    public ProductDTO updateRandomData(ProductDTO productDTO) {
        productDTO.setProductName(productDTO.getProductName() + "+" + serviceHelper.generateRandomWord());
        productRepository.saveAndFlush(serviceHelper.createProductFromDTO(productDTO,supplierRepository));
        Product productCheck = productRepository.getOne(productDTO.getProductId());
        return serviceHelper.createDTOFromProduct(productCheck);
    }

//    @Override
//    public ProductDTO updateRandomData(ProductDTO productDTO) {
//        productDTO.setProductName(productDTO.getProductName() + "+" + serviceHelper.generateRandomWord());
//        productRepository.update(serviceHelper.createProductFromDTO(productDTO,supplierRepository));
//        Product productCheck = productRepository.get(productDTO.getProductId());
//        return serviceHelper.createDTOFromProduct(productCheck);
//    }

    /**
     * Обновление екземпляра product и передача на запись в БД
     *
     * @param id          - id экземпляра product в базе, который необходимо изменить
     * @param productDTO- экземпляр productDTO, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    @Override
    public ProductDTO update(int id, ProductDTO productDTO) {
        Product updateProduct = productRepository.getOne(id);
        log.debug("updateProductWithId() Объект productDTO передан на обновление: {} ", productDTO);
        updateProduct.setProductName(productDTO.getProductName());
        updateProduct.setUnitPrice(productDTO.getUnitPrice());
        updateProduct.setDiscountinued(productDTO.isDiscountinued());
        log.info("updateProductWithId() Объект product успешно обновлен: {} ", updateProduct);
        productRepository.saveAndFlush(updateProduct);
        Product productCheck = productRepository.getOne(updateProduct.getProductId());
        return serviceHelper.createDTOFromProduct(productCheck);
    }

//    @Override
//    public ProductDTO update(int id, ProductDTO productDTO) {
//        Product updateProduct = productRepository.get(id);
//        log.debug("updateProductWithId() Объект productDTO передан на обновление: {} ", productDTO);
//        updateProduct.setProductName(productDTO.getProductName());
//        updateProduct.setUnitPrice(productDTO.getUnitPrice());
//        updateProduct.setDiscountinued(productDTO.isDiscountinued());
//        log.info("updateProductWithId() Объект product успешно обновлен: {} ", updateProduct);
//        productRepository.update(updateProduct);
//        Product productCheck = productRepository.get(updateProduct.getProductId());
//        return serviceHelper.createDTOFromProduct(productCheck);
//    }

    /**
     * Получение CustomerDTO из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полченного Customer
     */
    @Override
    public ProductDTO getProduct(int id) {
        Product product = productRepository.getOne(id);
        return serviceHelper.createDTOFromProduct(product);
    }

//    @Override
//    public ProductDTO getProduct(int id) {
//        Product product = productRepository.get(id);
//        return serviceHelper.createDTOFromProduct(product);
//    }

    /**
     * Получение всех CustomerDTO из базы
     *
     * @return - CustomerDTO созданный из полученного Customer
     */
    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(serviceHelper.createDTOFromProduct(product));
        }
        return productDTOList;
    }
//
//    @Override
//    public List<ProductDTO> getAllProduct() {
//        List<Product> productList = productRepository.getAllProduct();
//        List<ProductDTO> productDTOList = new ArrayList<>();
//        for (Product product : productList) {
//            productDTOList.add(serviceHelper.createDTOFromProduct(product));
//        }
//        return productDTOList;
//    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     */
    @Override
    public ProductDTO deleteById(int id) {
        ProductDTO productDTO = serviceHelper.createDTOFromProduct(productRepository.getOne(id));
        productRepository.deleteById(id);
        return productDTO;
    }

}