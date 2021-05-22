package com.zaraev.epam.javacourses.domain.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier", schema = "zaraev")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "company_name")
    private String companyName;

    private String phone;

    @OneToMany(mappedBy = "supplier", orphanRemoval = true)
    private List<Product> products = new ArrayList<>();  // поставщик один, а продуктов много
}