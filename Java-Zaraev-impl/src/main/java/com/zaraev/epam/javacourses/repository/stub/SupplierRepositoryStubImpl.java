package com.zaraev.epam.javacourses.repository.stub;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Класс для возврата застабленных значения класса Supplier без обращения к самой БД
 */
@Repository
@Profile("local")
@RequiredArgsConstructor
@Slf4j
public class SupplierRepositoryStubImpl implements SupplierRepository {

    @Value("${locale:en}")
    private Locale locale;

    /**
     * Запись в БД екземпляра Supplier - Ничего не сделает
     *
     * @return вернет застабленный экземпляр Supplier
     */
    @Override
    public Supplier create(Supplier supplier) {
        var stub = getStubSupplier();
        log.info("create() Объект supplier застаблен {}", stub);
        return stub;
    }

    /**
     * Изменение в БД экземпляра supplier - Ничего не сделает
     *
     * @param supplier - экземпляр supplier, который необходимо изменить
     */
    @Override
    public Supplier update(Supplier supplier) {
        var stub = getStubSupplier();
        log.info("create() Объект supplier застаблен");
        return stub;
    }

    /**
     * Получение из БД объекта Supplier - Ничего не сделает
     *
     * @param id - id объекта Supplier который необходимо получить
     * @return - вернет застабленный экземпляр Supplier
     */
    @Override
    public Supplier get(int id) {
        var stub = getStubSupplier();
        log.info("getSupplier() Объект supplier застаблен {}", stub);
        return stub;
    }

    /**
     * Получение из БД всех объектов Supplier - Ничего не сделает
     *
     * @return - Коллекция List  из застабленного Supplier
     */
    @Override
    public List<Supplier> getAllSupplier() {
        var stub = getStubSupplier();
        Supplier[] suppliers = {stub};
        List<Supplier> supplierList = Arrays.asList(suppliers);
        log.info("getAllSupplier() Объект supplier застаблен {}", suppliers);
        return supplierList;
    }

    /**
     * Удаление объекта Product из БД по id - Ничего не сделает
     *
     * @param id - id удаляемого Product
     */
    @Override
    public void delete(int id) {
        log.info("deleteSupplierWithId() Объект supplier застаблен");
    }

    private Supplier getStubSupplier() {
        var supplier = new Supplier();
        supplier.setSupplierId(1);
        supplier.setCompanyName("supplierCompanyName");
        supplier.setPhone("111111");
        return supplier;
    }
}