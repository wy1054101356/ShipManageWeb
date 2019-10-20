/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.model;

/**
 * 管理员
 */
public class Manager {

    private String Manager_id;
    private String Manager_password;
    private String Manager_Company_id;
    private String Manager_admin_type;
    private String Manager_state;
    private Company company;

    public Manager() {
    }

    public Manager(String Manager_id, String Manager_password) {
        this.Manager_id = Manager_id;
        this.Manager_password = Manager_password;
    }

    public Manager(String Manager_id, String Manager_password, String Manager_admin_type, String Manager_state) {
        this.Manager_id = Manager_id;
        this.Manager_password = Manager_password;
        this.Manager_admin_type = Manager_admin_type;
        this.Manager_state = Manager_state;
    }

    public Manager(String Manager_id, String Manager_password, String Manager_Company_id, String Manager_admin_type, String Manager_state) {
        this.Manager_id = Manager_id;
        this.Manager_password = Manager_password;
        this.Manager_Company_id = Manager_Company_id;
        this.Manager_admin_type = Manager_admin_type;
        this.Manager_state = Manager_state;
    }

    public Manager(String Manager_id, String Manager_password, String Manager_Company_id, String Manager_admin_type, String Manager_state, Company company) {
        this.Manager_id = Manager_id;
        this.Manager_password = Manager_password;
        this.Manager_Company_id = Manager_Company_id;
        this.Manager_admin_type = Manager_admin_type;
        this.Manager_state = Manager_state;
        this.company = company;
    }

    public String getManager_state() {
        return Manager_state;
    }

    public void setManager_state(String Manager_state) {
        this.Manager_state = Manager_state;
    }

    public String getManager_id() {
        return Manager_id;
    }

    public void setManager_id(String Manager_id) {
        this.Manager_id = Manager_id;
    }

    public String getManager_password() {
        return Manager_password;
    }

    public void setManager_password(String Manager_password) {
        this.Manager_password = Manager_password;
    }

    public String getManager_Company_id() {
        return Manager_Company_id;
    }

    public void setManager_Company_id(String Manager_Company_id) {
        this.Manager_Company_id = Manager_Company_id;
    }

    public String getManager_admin_type() {
        return Manager_admin_type;
    }

    public void setManager_admin_type(String Manager_admin_type) {
        this.Manager_admin_type = Manager_admin_type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
