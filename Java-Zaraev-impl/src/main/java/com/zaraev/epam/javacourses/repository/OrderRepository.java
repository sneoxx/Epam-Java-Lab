package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс для работы с OrderRepository
 */
@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer> {

}