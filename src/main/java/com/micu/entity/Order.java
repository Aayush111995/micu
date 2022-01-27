package com.micu.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Data
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private String amount;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "timeslot")
    private Time timeslot;

}