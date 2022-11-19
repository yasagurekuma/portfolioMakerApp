package com.example.PortfolioMaker.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String userName;
    @Column
    private String mailAddress;
    @Column
    private String password;
    @Column
    private Date dateOfBirth;
    @Column (insertable = false)
    private String color;
}

