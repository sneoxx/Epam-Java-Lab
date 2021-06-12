package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс для работы с ProductRepository
 */
@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer>  {

}