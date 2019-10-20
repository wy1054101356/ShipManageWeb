/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.model;

/**
 * 建造订单
 */
public class ConstructOrder {

    private String Construct_order_id;
    private String Construct_Company_id;
    private String Order_Company_id;
    private String Construct_order_date;
    private String Ship_type;
    private float Construct_value;
    private String Construct_order_state;

    public ConstructOrder() {
    }

    public ConstructOrder(String Construct_order_id, String Construct_Company_id, String Order_Company_id, String Construct_order_date, String Ship_type, float Construct_value, String Construct_order_state) {
        this.Construct_order_id = Construct_order_id;
        this.Construct_Company_id = Construct_Company_id;
        this.Order_Company_id = Order_Company_id;
        this.Construct_order_date = Construct_order_date;
        this.Ship_type = Ship_type;
        this.Construct_value = Construct_value;
        this.Construct_order_state = Construct_order_state;
    }

    public String getConstruct_order_state() {
        return Construct_order_state;
    }

    public void setConstruct_order_state(String Construct_order_state) {
        this.Construct_order_state = Construct_order_state;
    }

    public String getConstruct_order_id() {
        return Construct_order_id;
    }

    public void setConstruct_order_id(String Construct_order_id) {
        this.Construct_order_id = Construct_order_id;
    }

    public String getConstruct_Company_id() {
        return Construct_Company_id;
    }

    public void setConstruct_Company_id(String Construct_Company_id) {
        this.Construct_Company_id = Construct_Company_id;
    }

    public String getOrder_Company_id() {
        return Order_Company_id;
    }

    public void setOrder_Company_id(String Order_Company_id) {
        this.Order_Company_id = Order_Company_id;
    }

    public String getConstruct_order_date() {
        return Construct_order_date;
    }

    public void setConstruct_order_date(String Construct_order_date) {
        this.Construct_order_date = Construct_order_date;
    }

    public String getShip_type() {
        return Ship_type;
    }

    public void setShip_type(String Ship_type) {
        this.Ship_type = Ship_type;
    }

    public float getConstruct_value() {
        return Construct_value;
    }

    public void setConstruct_value(float Construct_value) {
        this.Construct_value = Construct_value;
    }

}
