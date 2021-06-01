package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.domain.entity.Product;
import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.dto.ProductDTO;
import com.zaraev.epam.javacourses.repository.ProductRepository;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import com.zaraev.epam.javacourses.service.EService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements EService {

    @Autowired
    private ProductRepository productRepository;// = new ProductRepository();

    @Autowired
    private SupplierRepository supplierRepository;// = new SupplierRepository();

    /**
     * Создание случайного supplier и передача на запись в БД экзмепляра supplier
     *
     * @param supplier - экземпляр supplier
     * @return - экземпляр product
     */
    public Product createRandomProduct(Supplier supplier) {
        Product product = new Product();
        product.setProductName(generateRandomWord());
        product.setDiscountinued(true);
        product.setUnitPrice(BigDecimal.valueOf(100));
        product.setSupplier(supplier);
        productRepository.create(product);
        return product;
    }

    /**
     * Создание и передача на запись в БД екземпляра product и передача на запись в БД
     *
     * @param productDTO - Экземпляр productDTO
     * @return - результат опрерации orderDTO
     */
    public ProductDTO create(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setDiscountinued(productDTO.isDiscountinued());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setSupplier(supplierRepository.getSupplier(productDTO.getSupplierId()));
        productRepository.create(product);
        Product productCheck = productRepository.getProductWithInstance(product.getProductName());
        return createProductDTO(productCheck);
    }

    /**
     * Обновление екземпляра product и передача на запись в БД
     *
     * @param product - экземпляр product, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    public ProductDTO update(Product product) {
        product.setProductName(product.getProductName() + "+" + generateRandomWord());
        productRepository.update(product);
        Product productCheck = productRepository.getProductWithInstance(product.getProductName());
        return createProductDTO(productCheck);
    }

    /**
     * Обновление екземпляра product и передача на запись в БД
     *
     * @param id      - id экземпляра product в базе, который необходимо изменить
     * @param productDTO- экземпляр productDTO, на который необходимо изменить
     * @return - результат опрерации orderDTO
     */
    public ProductDTO updateProductWithId(int id, ProductDTO productDTO) {
        Product updateProduct = productRepository.getProduct(id);
        log.debug("updateProductWithId() Объект productDTO передан на обновление: {} ", productDTO);
        updateProduct.setProductName(productDTO.getProductName());
        updateProduct.setUnitPrice(productDTO.getUnitPrice());
        updateProduct.setDiscountinued(productDTO.isDiscountinued());
        log.info("updateProductWithId() Объект product успешно обновлен: {} ", updateProduct);
        productRepository.update(updateProduct);
        Product productCheck = productRepository.getProductWithInstance(updateProduct.getProductName());
        return createProductDTO(productCheck);
    }

    /**
     * Получение CustomerDTO из базы
     *
     * @param id - id Customer, которое необходимло получить
     * @return - CustomerDTO созданный из полченного Customer
     */
    public ProductDTO getProduct(int id) {
        Product product = productRepository.getProduct(id);
        return createProductDTO(product);
    }

    /**
     * Получение всех CustomerDTO из базы
     *
     * @return - CustomerDTO созданный из полученного Customer
     */
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productRepository.getAllProduct();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(createProductDTO(product));
        }
        return productDTOList;
    }

    /**
     * Удаление Customer из базы по id
     *
     * @param id - id Customer для удаления
     */
    public void deleteProductWithId(int id) {
        productRepository.deleteProductWithId(id);
    }

    /**
     * Создание ProductDTO из product
     *
     * @param product - исходный product
     * @return - полученный ProductDTO
     */
    public ProductDTO createProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setDiscountinued(product.isDiscountinued());
        productDTO.setUnitPrice(product.getUnitPrice());
        productDTO.setSupplierId(product.getSupplier().getSupplierId());
        return productDTO;
    }

    /**
     * Генерация случайного слова
     *
     * @return - случайное слово
     */
    public static String generateRandomWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(2) + 3];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }
}