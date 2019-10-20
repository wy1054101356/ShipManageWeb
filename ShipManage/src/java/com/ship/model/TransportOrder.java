/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.model;

/**
 * 运输订单
 */
public class TransportOrder {

    private String Transport_order_id;
    private String Ship_id;
    private String Order_Company_id;
    private String Transport_Company_id;
    private String Transport_Order_date;
    private String Transport_start;
    private String Transport_order_state;
    private String Transport_order_deal;
    private float Transport_value;

    public TransportOrder() {

    }

    public TransportOrder(String Transport_order_id, String Ship_id, String Order_Company_id, String Transport_Company_id, String Transport_Order_date, String Transport_start, String Transport_order_state, String Transport_order_deal, float Transport_value) {
        this.Transport_order_id = Transport_order_id;
        this.Ship_id = Ship_id;
        this.Order_Company_id = Order_Company_id;
        this.Transport_Company_id = Transport_Company_id;
        this.Transport_Order_date = Transport_Order_date;
        this.Transport_start = Transport_start;
        this.Transport_order_state = Transport_order_state;
        this.Transport_order_deal = Transport_order_deal;
        this.Transport_value = Transport_value;
    }

    public String getTransport_order_id() {
        return Transport_order_id;
    }

    public void setTransport_order_id(String Transport_order_id) {
        this.Transport_order_id = Transport_order_id;
    }

    public String getShip_id() {
        return Ship_id;
    }

    public void setShip_id(String Ship_id) {
        this.Ship_id = Ship_id;
    }

    public String getOrder_Company_id() {
        return Order_Company_id;
    }

    public void setOrder_Company_id(String Order_Company_id) {
        this.Order_Company_id = Order_Company_id;
    }

    public String getTransport_Company_id() {
        return Transport_Company_id;
    }

    public void setTransport_Company_id(String Transport_Company_id) {
        this.Transport_Company_id = Transport_Company_id;
    }

    public String getTransport_Order_date() {
        return Transport_Order_date;
    }

    public void setTransport_Order_date(String Transport_Order_date) {
        this.Transport_Order_date = Transport_Order_date;
    }

    public String getTransport_start() {
        return Transport_start;
    }

    public void setTransport_start(String Transport_start) {
        this.Transport_start = Transport_start;
    }

    public String getTransport_order_state() {
        return Transport_order_state;
    }

    public void setTransport_order_state(String Transport_order_state) {
        this.Transport_order_state = Transport_order_state;
    }

    public String getTransport_order_deal() {
        return Transport_order_deal;
    }

    public void setTransport_order_deal(String Transport_order_deal) {
        this.Transport_order_deal = Transport_order_deal;
    }

    public float getTransport_value() {
        return Transport_value;
    }

    public void setTransport_value(float Transport_value) {
        this.Transport_value = Transport_value;
    }

}
