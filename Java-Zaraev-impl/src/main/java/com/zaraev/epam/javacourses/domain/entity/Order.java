package com.zaraev.epam.javacourses.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity класс для работы с таблицей order базы данных
 */
@Data
@NoArgsConstructor
@Entity
@Table(schema = "zaraev")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    private String orderNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;  // клиент один, а заказов много

    private Timestamp orderDate;

    private BigDecimal totalAmount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(name ="product_id",referencedColumnName = "product_id")
    )
    private Set<Product> products = new HashSet<>();
}