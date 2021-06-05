package com.zaraev.epam.javacourses.repository.stub;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.helper.RepositoryHelper;
import com.zaraev.epam.javacourses.repository.impl.ISupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;

@Profile("local")
@Component
@Slf4j
public class SupplierRepositoryStub implements ISupplierRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private RepositoryHelper repositoryHelper;

    /**
     * Запись в БД екземпляра Supplier - Ничего не сделает
     *
     * @return вернет застабленный экземпляр Supplier
     */
    @Override
    public Supplier create(Supplier supplier) {
        var stub = repositoryHelper.supplier();
            log.info("create() Объект supplier застаблен {}", stub);
            return (Supplier) stub;
        }
   
    /**
     * Изменение в БД экземпляра supplier - Ничего не сделает
     *
     * @param supplier - экземпляр supplier, который необходимо изменить
     */
    @Override
    public void update(Supplier supplier) {
            log.info("create() Объект supplier застаблен");
            return ;
        }

    /**
     * Получение из БД объекта Supplier - Ничего не сделает
     *
     * @param id - id объекта Supplier который необходимо получить
     * @return - вернет застабленный экземпляр Supplier
     */
    @Override
    public Supplier getSupplier(int id) {
        var stub = repositoryHelper.supplier();
            log.info("getSupplier() Объект supplier застаблен {}", stub);
            return (Supplier) stub;
        }

    /**
     * Получение из БД объекта Order по полю orderNumber Order - Ничего не сделает
     *
     * @param companyName - id объекта Order который необходимо получить
     * @return - вернет застабленный экземпляр Supplier
     */
    @Override
    public Supplier getSupplierWithInstance(String companyName) {
        var stub = repositoryHelper.supplier();
            log.info("getOrderWithInstance() Объект supplier застаблен {}", stub);
            return (Supplier) stub;
        }

    /**
     * Получение из БД всех объектов Supplier - Ничего не сделает
     *
     * @return - Коллекция List  из застабленного Supplier
     */
    @Override
    public List<Supplier> getAllSupplier() {
        var stub = repositoryHelper.supplier();
            Supplier[] suppliers = {stub};
            List<Supplier> supplierList = Arrays.asList(suppliers);
            log.info("getAllSupplier() Объект supplier застаблен {}", suppliers);
            return supplierList;
        }

    /**
     * Удаление объекта supplier из БД - Ничего не сделает
     *
     * @param supplier - удаляемый объект
     */
    @Override
    public void deleteSupplier(Supplier supplier) {
            log.info("deleteSupplier() Объект supplier застаблен");
            return;
        }

    /**
     * Удаление объекта Product из БД по id - Ничего не сделает
     *
     * @param id - id удаляемого Product
     */
    @Override
    public void deleteSupplierWithId(int id) {
            log.info("deleteSupplierWithId() Объект supplier застаблен");
            return;
        }
}