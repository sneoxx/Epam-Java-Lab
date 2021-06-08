package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс для работы с SupplierRepository
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>  {

}