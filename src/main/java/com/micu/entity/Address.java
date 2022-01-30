package com.micu.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name="locality")
    private String locality;

    @Column(name="city")
    private String city;

    @Column(name="pincode")
    private String pincode;

    @Column(name="lat")
    private String lat;

    @Column(name="long")
    private String longi;

    @Column(name="address")
    private String address;

}
