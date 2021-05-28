package com.zaraev.epam.javacourses.domain.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity класс для работы с таблицей Supplier базы данных
 */
@NoArgsConstructor
@Data
@Entity
@Table
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer supplierId;

    private String companyName;

    private String phone;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private static List<Product> products = new ArrayList<>();  // поставщик один, а продуктов много
}