package com.example.PortfolioMaker.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class TestForm {

    private int id;

    private String name;
}
