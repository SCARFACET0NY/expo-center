package com.anton.expo.repository.dto;

import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.repository.entity.Ticket;

public class TicketDto {
    private Ticket ticket;
    private Exposition exposition;

    public TicketDto() {}

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }
}
