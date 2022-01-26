package com.micu.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "delivery_boy")
public class DeliveryBoy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "created_date")
    private Date createdDate;
}