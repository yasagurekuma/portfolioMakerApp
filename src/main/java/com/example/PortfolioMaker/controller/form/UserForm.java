package com.example.PortfolioMaker.controller.form;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UserForm {

    private int id;

    private String name;

    private String userName;

    private String mailAddress;

    private String password;

    private Date dateOfBirth;

    private String color;
}
