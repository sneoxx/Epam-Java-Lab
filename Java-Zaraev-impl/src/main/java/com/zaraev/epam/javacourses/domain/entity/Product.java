package com.zaraev.epam.javacourses.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity класс для работы с таблицей product базы данных
 */
@NoArgsConstructor
@Data
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;  // поставщик один, а продуктов много

    private String productName;

    private BigDecimal unitPrice;

    private boolean isDiscountinued;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();
}