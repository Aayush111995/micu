package com.micu.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "delivery_order")
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany
    @JoinColumn(name = "delivery_boy_id")
    private DeliveryBoy deliveryBoy;

    @OneToMany
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "status")
    private String status;

    @Column(name = "source")
    private String source;



}