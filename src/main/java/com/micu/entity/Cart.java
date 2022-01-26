package com.micu.entity;

import com.micu.entity.*;

import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "product_json")
    private String productjson;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "payable_amount")
    private String payableAmount;

    @Column(name = "total_amount")
    private String totalAmount;


    @Column(name = "discount_amount")
    private String discountAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "created_date")
    private Date createdDate;

}