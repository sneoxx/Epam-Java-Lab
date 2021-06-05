package com.zaraev.epam.javacourses.repository.impl;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.repository.ISupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Profile("!local")
@Component
@Slf4j
public class SupplierRepository implements ISupplierRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * Запись в БД екземпляра Supplier
     *
     * @return вернет занесенный экземпляр Supplier
     */
    @Override
    public Supplier create(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(supplier);
            transaction.commit();
            entityManager.close();
            log.info("createSupplier() Объект Supplier создан и занесен в БД: {}", supplier);
            return supplier;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Изменение в БД экземпляра supplier
     *
     * @param supplier - экземпляр supplier, который необходимо изменить
     */
    @Override
    public void update(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            log.debug("updateSupplier() Объект supplier передан на обновление: {} ", supplier);
            transaction.begin();
            entityManager.merge(supplier);
            transaction.commit();
            log.info("updateSupplier() Объект supplier успешно обновлен: {} ", supplier);
            entityManager.close();
        } catch (Exception e) {
            log.error("updateSupplier() Ошибка обновления объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Получение из БД объекта Supplier
     *
     * @param id - id объекта Supplier который необходимо получить
     * @return - объект Supplier из БД
     */
    @Override
    public Supplier getSupplier(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Supplier supplier = null;
        try {
            supplier = entityManager.find(Supplier.class, id);
            log.info("getSupplier() Объект supplier успешно получен из БД {}", supplier);
            entityManager.close();
            return supplier;
        } catch (Exception e) {
            log.error("getSupplier() Ошибка получения из БД объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
        return supplier;
    }

    /**
     * Получение из БД объекта Order по полю orderNumber Order
     *
     * @param companyName - id объекта Order который необходимо получить
     * @return - объект Order из БД
     */
    @Override
    public Supplier getSupplierWithInstance(String companyName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Supplier supplier = null;
        try {
            TypedQuery<Supplier> query = entityManager.createQuery(
                    "SELECT u FROM Supplier u WHERE u.companyName = :companyName", Supplier.class);
            supplier = query.setParameter("companyName", companyName)
                    .getSingleResult();
            log.info("getCustomer() Объект supplier успешно получен из БД {}", supplier);
            entityManager.close();
            return supplier;
        } catch (Exception e) {
            log.error("getCustomer() Ошибка получения из БД объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
        return supplier;
    }

    /**
     * Получение из БД всех объектов Supplier
     *
     * @return - Коллекция List всех объектов Supplier из БД
     */
    @Override
    public List<Supplier> getAllSupplier() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Supplier> supplierList = new ArrayList<>();
        try {
            String query = "SELECT s FROM Supplier s";
            supplierList = entityManager
                    .createQuery(query, Supplier.class)
                    .getResultList();
            entityManager.close();
            log.info("getAllSupplier() Выведен список всех Supplier: {}", supplierList);
            return supplierList;
        } catch (Exception e) {
            log.error("getAllSupplier() Ошибка получения из БД объектов supplier: ", e);
        } finally {
            entityManager.close();
        }
        return supplierList;
    }

    /**
     * Удаление объекта supplier из БД
     *
     * @param supplier - удаляемый объект
     */
    @Override
    public void deleteSupplier(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Supplier tempSupplier = entityManager.find(Supplier.class, supplier.getSupplierId());
            transaction.begin();
            log.debug("deleteSupplier() Объект supplier передан на удаление: {}", tempSupplier);
            if (entityManager.contains(tempSupplier)) {
                entityManager.remove(tempSupplier);
                transaction.commit();
                log.info("deleteSupplier() Объект supplier успешно удален: {}", tempSupplier);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteSupplier() Ошибка удаления объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Удаление объекта Product из БД по id
     *
     * @param id - id удаляемого Product
     */
    @Override
    public void deleteSupplierWithId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Supplier tempSupplier = entityManager.find(Supplier.class, id);
            transaction.begin();
            log.debug("deleteSupplier() Объект supplier передан на удаление: {}", tempSupplier);
            if (entityManager.contains(tempSupplier)) {
                entityManager.remove(tempSupplier);
                transaction.commit();
                log.info("deleteSupplier() Объект supplier успешно удален: {}", tempSupplier);
            }
            entityManager.close();
        } catch (Exception e) {
            log.error("deleteSupplier() Ошибка удаления объекта supplier: ", e);
        } finally {
            entityManager.close();
        }
    }
}