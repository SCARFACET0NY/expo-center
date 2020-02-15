package com.anton.expo.repository.dao;

import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Payment;
import com.anton.expo.repository.entity.Ticket;

import java.util.List;

public interface PaymentDao extends Dao<Payment> {
    List<TicketDto> getTicketsByPaymentId(long id);

    long savePaymentWithTickets(Payment payment, List<Ticket> tickets);
}
