package com.zaraev.epam.javacourses.repository.impl;

import com.zaraev.epam.javacourses.domain.entity.Order;
import com.zaraev.epam.javacourses.domain.entity.Product;

public interface IOrderProductRepository {

   void createBondOrderAndProduct (Order order, Product product);

}