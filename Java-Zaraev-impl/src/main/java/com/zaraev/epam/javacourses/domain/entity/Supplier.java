package com.zaraev.epam.javacourses.domain.entity;


import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity класс для работы с таблицей Supplier базы данных
 */
@Component
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier")
public class Supplier implements IEntity{

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer supplierId;

    @Expose
    private String companyName;

    @Expose
    private String phone;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private static List<Product> products = new ArrayList<>();  // поставщик один, а продуктов много

}