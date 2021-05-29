package com.zaraev.epam.javacourses.domain.entity;

import com.google.gson.annotations.Expose;
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

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Expose
    private String productName;

    @Expose
    private BigDecimal unitPrice;

    @Expose
    private boolean isDiscountinued;

    @Expose
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;  // поставщик один, а продуктов много

    @Expose(serialize = false)
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();
}