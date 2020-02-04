package com.anton.expo.services;

import com.anton.expo.factory.DaoFactory;
import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.dao.TicketDao;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.repository.entity.Ticket;

import java.time.LocalDate;
import java.util.Map;

public class TicketService {
    public static final int ONE_TICKET = 1;
    private ExpositionDao expositionDao = DaoFactory.getExpositionDao();
    private TicketDao ticketDao = DaoFactory.getTicketDao();

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

    public void saveTicket(Ticket ticket, long payment_id) {
        ticket.setPaymentId(payment_id);
        ticketDao.save(ticket);
    }

    public double getCartTotal(Map<String, TicketDto> cart) {
        return Math.round(cart.values().stream()
                .mapToDouble(item -> item.getExposition().getPrice() * item.getTicket().getQuantity())
                .reduce(0.0, Double::sum) * 100) / 100.0;
    }
}
