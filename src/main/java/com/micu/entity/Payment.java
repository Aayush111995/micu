package com.micu.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "payment_response")
    private String paymentResponse;

    @Column(name = "amount")
    private String amount;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "pg")
    private String pg;

    @Column(name = "status")
    private String status;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;
}