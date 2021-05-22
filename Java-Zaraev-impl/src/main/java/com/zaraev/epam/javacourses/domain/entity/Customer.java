package com.zaraev.epam.javacourses.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "customer", schema = "zaraev")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private int customerID;

    @Column(name = "customer_name")
    private String customerName;

    private String phone;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private static List<Order> orders = new ArrayList<>();    // клиент один а заказов много

}