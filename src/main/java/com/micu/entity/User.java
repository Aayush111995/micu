package com.micu.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="mobile_number")
    private String mobile_number;

    @Column(name="email")
    private String email;

    @Column(name="created_date")
    private Date created_date;


}
