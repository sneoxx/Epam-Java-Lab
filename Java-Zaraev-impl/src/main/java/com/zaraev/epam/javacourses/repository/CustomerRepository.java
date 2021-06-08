package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс для работы с CustomerRepository
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

 }
