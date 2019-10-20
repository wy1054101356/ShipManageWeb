/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.model;

/**
 *
 * @author 思之声
 */
public class Message {

    private String Message_id;
    private String Message_name;
    private String Message_type;
    private String Message_Date;
    private String Message_title;
    private String Message_detail;
    private String Message_state;

    public Message() {
    }

    public Message(String Message_id, String Message_name, String Message_type, String Message_Date, String Message_title, String Message_detail, String Message_state) {
        this.Message_id = Message_id;
        this.Message_name = Message_name;
        this.Message_type = Message_type;
        this.Message_Date = Message_Date;
        this.Message_title = Message_title;
        this.Message_detail = Message_detail;
        this.Message_state = Message_state;
    }

    public String getMessage_id() {
        return Message_id;
    }

    public void setMessage_id(String Message_id) {
        this.Message_id = Message_id;
    }

    public String getMessage_name() {
        return Message_name;
    }

    public void setMessage_name(String Message_name) {
        this.Message_name = Message_name;
    }

    public String getMessage_type() {
        return Message_type;
    }

    public void setMessage_type(String Message_type) {
        this.Message_type = Message_type;
    }

    public String getMessage_Date() {
        return Message_Date;
    }

    public void setMessage_Date(String Message_Date) {
        this.Message_Date = Message_Date;
    }

    public String getMessage_title() {
        return Message_title;
    }

    public void setMessage_title(String Message_title) {
        this.Message_title = Message_title;
    }

    public String getMessage_detail() {
        return Message_detail;
    }

    public void setMessage_detail(String Message_detail) {
        this.Message_detail = Message_detail;
    }

    public String getMessage_state() {
        return Message_state;
    }

    public void setMessage_state(String Message_state) {
        this.Message_state = Message_state;
    }

}
