package com.zaraev.epam.javacourses.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity класс для работы с таблицей customer базы данных
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;

    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private static List<Order> orders = new ArrayList<>();    // клиент один а заказов много

}