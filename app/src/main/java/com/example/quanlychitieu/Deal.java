package com.example.quanlychitieu;

import java.io.Serializable;

public class Deal implements Serializable {
    private long id;
    private String name;
    private String note;
    private double price;



    public Deal(long id, String name, String note, double price) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
