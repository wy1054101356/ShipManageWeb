/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.model;

/**
 * 船舶信息
 */
public class Ship {

    private String Ship_id;
    private String Ship_name;
    private String Ship_type;
    private float Ship_load;
    private float Ship_weight;
    private String Construct_Company_id;
    private String Owner_Company_id;
    private String Ship_construct_date;
    private float Ship_value;
    private String Ship_state;

    public Ship(String Ship_id, String Ship_name, String Ship_type, float Ship_load, float Ship_weight, String Construct_Company_id, String Owner_Company_id, String Ship_construct_date, float Ship_value, String Ship_state) {
        this.Ship_id = Ship_id;
        this.Ship_name = Ship_name;
        this.Ship_type = Ship_type;
        this.Ship_load = Ship_load;
        this.Ship_weight = Ship_weight;
        this.Construct_Company_id = Construct_Company_id;
        this.Owner_Company_id = Owner_Company_id;
        this.Ship_construct_date = Ship_construct_date;
        this.Ship_value = Ship_value;
        this.Ship_state = Ship_state;
    }

    public Ship() {

    }

    public String getShip_id() {
        return Ship_id;
    }

    public void setShip_id(String Ship_id) {
        this.Ship_id = Ship_id;
    }

    public String getShip_name() {
        return Ship_name;
    }

    public void setShip_name(String Ship_name) {
        this.Ship_name = Ship_name;
    }

    public String getShip_type() {
        return Ship_type;
    }

    public void setShip_type(String Ship_type) {
        this.Ship_type = Ship_type;
    }

    public float getShip_load() {
        return Ship_load;
    }

    public void setShip_load(float Ship_load) {
        this.Ship_load = Ship_load;
    }

    public float getShip_weight() {
        return Ship_weight;
    }

    public void setShip_weight(float Ship_weight) {
        this.Ship_weight = Ship_weight;
    }

    public String getConstruct_Company_id() {
        return Construct_Company_id;
    }

    public void setConstruct_Company_id(String Construct_Company_id) {
        this.Construct_Company_id = Construct_Company_id;
    }

    public String getOwner_Company_id() {
        return Owner_Company_id;
    }

    public void setOwner_Company_id(String Owner_Company_id) {
        this.Owner_Company_id = Owner_Company_id;
    }

    public String getShip_construct_date() {
        return Ship_construct_date;
    }

    public void setShip_construct_date(String Ship_construct_date) {
        this.Ship_construct_date = Ship_construct_date;
    }

    public float getShip_value() {
        return Ship_value;
    }

    public void setShip_value(float Ship_value) {
        this.Ship_value = Ship_value;
    }

    public String getShip_state() {
        return Ship_state;
    }

    public void setShip_state(String Ship_state) {
        this.Ship_state = Ship_state;
    }

}
