package com.anton.expo.services.impl;

import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.dao.PaymentDao;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.repository.entity.Payment;
import com.anton.expo.repository.entity.Ticket;
import com.anton.expo.services.PaymentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {
    private ExpositionDao expositionDao;
    private PaymentDao paymentDao;

    public PaymentServiceImpl(ExpositionDao expositionDao, PaymentDao paymentDao) {
        this.expositionDao = expositionDao;
        this.paymentDao = paymentDao;
    }

    public TicketDto createTicketDto(long expositionId) {
        TicketDto ticketDto = new TicketDto();
        Exposition exposition = expositionDao.get(expositionId);

        LocalDate date = null;
        if (LocalDate.now().isAfter(exposition.getStartDate())) {
            date = LocalDate.now();
        } else {
            date = exposition.getStartDate();
        }
        Ticket ticket = Ticket.builder().quantity(ONE_TICKET).date(date).expositionId(expositionId).build();

        ticketDto.setTicket(ticket);
        ticketDto.setExposition(exposition);

        return ticketDto;
    }

    public Payment createPayment(double total, long userId) {
        return Payment.builder().usedId(userId).date(LocalDateTime.now()).total(total).build();
    }

    public double getCartTotal(Map<String, TicketDto> cart) {
        return Math.round(cart.values().stream()
                .mapToDouble(item -> item.getExposition().getPrice() * item.getTicket().getQuantity())
                .reduce(0.0, Double::sum) * 100) / 100.0;
    }

    public long savePayment(double total, long userId, Map<String, TicketDto> cart) {
        List<Ticket> tickets = new ArrayList<>();
        cart.values().forEach(ticketDto -> tickets.add(ticketDto.getTicket()));

        return paymentDao.savePaymentWithTickets(createPayment(total, userId), tickets);
    }
}
