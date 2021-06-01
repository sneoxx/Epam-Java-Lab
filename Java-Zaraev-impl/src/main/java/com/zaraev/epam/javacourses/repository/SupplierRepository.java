package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import com.zaraev.epam.javacourses.service.EService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class SupplierRepository implements EService {

//    @PersistenceContext( type = PersistenceContextType.TRANSACTION)
//    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;// = Persistence.createEntityManagerFactory("WER");

    /**
     * Запись в БД екземпляра Supplier
     *
     * @return вернет занесенный экземпляр Supplier
     */
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
    public void update(Supplier supplier) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            log.debug("updateSupplier() Объект supplier передан на обновление: {} ", supplier);
            //  supplier.setCompanyName(supplier.getCompanyName() + "+" + generateRandomWord());
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
    public Supplier getOrderWithInstance(String companyName) {
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


//
//
//    /**
//     * Запись в БД екземпляра Supplier
//     *
//     * @return вернет занесенный экземпляр Supplier
//     */
//    @Transactional
//    public Supplier create(Supplier supplier) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        EntityTransaction transaction = entityManager.getTransaction();
//        try {
////            transaction.begin();
//            entityManager.persist(supplier);
////            transaction.commit();
//            entityManager.close();
//            log.info("createSupplier() Объект Supplier создан и занесен в БД: {}", supplier);
//            return supplier;
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    /**
//     * Изменение в БД экземпляра supplier
//     *
//     * @param supplier - экземпляр supplier, который необходимо изменить
//     */
//    @Transactional
//    public void update(Supplier supplier) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            log.debug("updateSupplier() Объект supplier передан на обновление: {} ", supplier);
//            //  supplier.setCompanyName(supplier.getCompanyName() + "+" + generateRandomWord());
////            transaction.begin();
//            entityManager.merge(supplier);
////            transaction.commit();
//            log.info("updateSupplier() Объект supplier успешно обновлен: {} ", supplier);
//            entityManager.close();
//        } catch (Exception e) {
//            log.error("updateSupplier() Ошибка обновления объекта supplier: ", e);
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    /**
//     * Получение из БД объекта Supplier
//     *
//     * @param id - id объекта Supplier который необходимо получить
//     * @return - объект Supplier из БД
//     */
//    public Supplier getSupplier(int id) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Supplier supplier = null;
//        try {
//            supplier = entityManager.find(Supplier.class, id);
//            log.info("getSupplier() Объект supplier успешно получен из БД {}", supplier);
//            entityManager.close();
//            return supplier;
//        } catch (Exception e) {
//            log.error("getSupplier() Ошибка получения из БД объекта supplier: ", e);
//        } finally {
//            entityManager.close();
//        }
//        return supplier;
//    }
//
//    /**
//     * Получение из БД объекта Order по полю orderNumber Order
//     *
//     * @param companyName - id объекта Order который необходимо получить
//     * @return - объект Order из БД
//     */
//    public Supplier getOrderWithInstance(String companyName) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Supplier supplier = null;
//        try {
//            TypedQuery<Supplier> query = entityManager.createQuery(
//                    "SELECT u FROM Supplier u WHERE u.companyName = :companyName", Supplier.class);
//            supplier = query.setParameter("companyName", companyName)
//                    .getSingleResult();
//            log.info("getCustomer() Объект supplier успешно получен из БД {}", supplier);
//            entityManager.close();
//            return supplier;
//        } catch (Exception e) {
//            log.error("getCustomer() Ошибка получения из БД объекта supplier: ", e);
//        } finally {
//            entityManager.close();
//        }
//        return supplier;
//    }
//
//    /**
//     * Получение из БД всех объектов Supplier
//     *
//     * @return - Коллекция List всех объектов Supplier из БД
//     */
//    public List<Supplier> getAllSupplier() {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<Supplier> supplierList = new ArrayList<>();
//        try {
//            String query = "SELECT s FROM Supplier s";
//            supplierList = entityManager
//                    .createQuery(query, Supplier.class)
//                    .getResultList();
//            entityManager.close();
//            log.info("getAllSupplier() Выведен список всех Supplier: {}", supplierList);
//            return supplierList;
//        } catch (Exception e) {
//            log.error("getAllSupplier() Ошибка получения из БД объектов supplier: ", e);
//        } finally {
//            entityManager.close();
//        }
//        return supplierList;
//    }
//
//    /**
//     * Удаление объекта supplier из БД
//     *
//     * @param supplier - удаляемый объект
//     */
//    @Transactional
//    public void deleteSupplier(Supplier supplier) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            Supplier tempSupplier = entityManager.find(Supplier.class, supplier.getSupplierId());
////            transaction.begin();
//            log.debug("deleteSupplier() Объект supplier передан на удаление: {}", tempSupplier);
//            if (entityManager.contains(tempSupplier)) {
//                entityManager.remove(tempSupplier);
////                transaction.commit();
//                log.info("deleteSupplier() Объект supplier успешно удален: {}", tempSupplier);
//            }
//            entityManager.close();
//        } catch (Exception e) {
//            log.error("deleteSupplier() Ошибка удаления объекта supplier: ", e);
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    /**
//     * Удаление объекта Product из БД по id
//     *
//     * @param id - id удаляемого Product
//     */
//    @Transactional
//    public void deleteSupplierWithId(int id) {
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            Supplier tempSupplier = entityManager.find(Supplier.class, id);
////            transaction.begin();
//            log.debug("deleteSupplier() Объект supplier передан на удаление: {}", tempSupplier);
//            if (entityManager.contains(tempSupplier)) {
//                entityManager.remove(tempSupplier);
////                transaction.commit();
//                log.info("deleteSupplier() Объект supplier успешно удален: {}", tempSupplier);
//            }
//            entityManager.close();
//        } catch (Exception e) {
//            log.error("deleteSupplier() Ошибка удаления объекта supplier: ", e);
//        } finally {
//            entityManager.close();
//        }
//    }


}