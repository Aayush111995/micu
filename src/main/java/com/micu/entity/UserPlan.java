package com.micu.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "user_plan")
public class UserPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private String amount;

    @Column(name = "plan_type")
    private String planType;

    @Column(name = "status")
    private String status;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "expiry_date")
    private Date expiryDate;
}