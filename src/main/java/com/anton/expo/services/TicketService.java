package com.anton.expo.services;

import com.anton.expo.factory.DaoFactory;
import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Ticket;

import java.time.LocalDate;
import java.util.Map;

public class TicketService {
    public static final int ONE_TICKET = 1;
    private ExpositionDao expositionDao = DaoFactory.getExpositionDao();

    public TicketDto createTicketDto(long expositionId) {
        TicketDto ticketDto = new TicketDto();
        Ticket ticket = new Ticket();
        ticket.setQuantity(ONE_TICKET);
        ticket.setDate(LocalDate.now());

        ticketDto.setTicket(ticket);
        ticketDto.setExposition(expositionDao.get(expositionId));

        return ticketDto;
    }

    public double getCartTotal(Map<String, TicketDto> cart) {
        return cart.values().stream()
                .mapToDouble(item -> item.getExposition().getPrice() * item.getTicket().getQuantity())
                .reduce(0.0, Double::sum);
    }
}
