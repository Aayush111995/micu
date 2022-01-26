package com.micu.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mrp")
    private String mrp;

    @Column(name = "product_meta_date")
    private String productMetaDate;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "unit")
    private String unit;

    @Column(name = "discounted_percentage")
    private String discounted_percentage;

    @Column(name = "created_date")
    private Date createdDate;
}