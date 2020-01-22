package com.anton.expo.repository.entity;

import java.time.LocalDate;

public class Ticket {
    private long id;
    private LocalDate date;
    private int quantity;
    private long expositionId;
    private long paymentId;

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

    public long getExpositionId() {
        return expositionId;
    }

    public void setExpositionId(long expositionId) {
        this.expositionId = expositionId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }
}
