/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.model;

/**
 * 企业信息
 */
public class Company {

    private String Company_id;
    private String Company_name;
    private String Company_tel;
    private String Company_address;
    private String Company_type;
    private String Company_state;

    public Company(String Company_id, String Company_name, String Company_tel, String Company_address, String Company_type, String Company_state) {
        this.Company_id = Company_id;
        this.Company_name = Company_name;
        this.Company_tel = Company_tel;
        this.Company_address = Company_address;
        this.Company_type = Company_type;
        this.Company_state = Company_state;
    }

    public Company() {

    }

    public Company(Company company) {
        this.Company_id = company.getCompany_id();
        this.Company_name = company.getCompany_name();
        this.Company_tel = company.getCompany_tel();
        this.Company_address = company.getCompany_address();
        this.Company_type = company.getCompany_type();
        this.Company_state = company.getCompany_state();
    }

    public String getCompany_id() {
        return Company_id;
    }

    public void setCompany_id(String Company_id) {
        this.Company_id = Company_id;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String Company_name) {
        this.Company_name = Company_name;
    }

    public String getCompany_tel() {
        return Company_tel;
    }

    public void setCompany_tel(String Company_tel) {
        this.Company_tel = Company_tel;
    }

    public String getCompany_address() {
        return Company_address;
    }

    public void setCompany_address(String Company_address) {
        this.Company_address = Company_address;
    }

    public String getCompany_type() {
        return Company_type;
    }

    public void setCompany_type(String Company_type) {
        this.Company_type = Company_type;
    }

    public String getCompany_state() {
        return Company_state;
    }

    public void setCompany_state(String Company_state) {
        this.Company_state = Company_state;
    }

}
