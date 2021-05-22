package com.zaraev.epam.javacourses.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "product", schema = "zaraev")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;  // поставщик один, а продуктов много

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private BigDecimal unitprice;

    @Column(name = "is_discountinued")
    private boolean isDiscount;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();
}