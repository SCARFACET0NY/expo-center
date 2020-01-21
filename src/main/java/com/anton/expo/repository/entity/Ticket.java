package com.anton.expo.repository.entity;

import java.time.LocalDate;

public class Ticket {
    private long id;
    private LocalDate date;
    private int quantity;

    public Ticket() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
