package com.anton.expo.services;

import com.anton.expo.factory.DaoFactory;
import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.repository.entity.Ticket;

import java.time.LocalDate;
import java.util.Map;

public class TicketService {
    public static final int ONE_TICKET = 1;
    private ExpositionDao expositionDao = DaoFactory.getExpositionDao();

    public TicketDto createTicketDto(long expositionId) {
        TicketDto ticketDto = new TicketDto();
        Ticket ticket = new Ticket();
        Exposition exposition = expositionDao.get(expositionId);

        ticket.setQuantity(ONE_TICKET);
        if (LocalDate.now().isAfter(exposition.getStartDate())) {
            ticket.setDate(LocalDate.now());
        } else {
            ticket.setDate(exposition.getStartDate());
        }
        ticketDto.setTicket(ticket);
        ticketDto.setExposition(exposition);

        return ticketDto;
    }

    public double getCartTotal(Map<String, TicketDto> cart) {
        return cart.values().stream()
                .mapToDouble(item -> item.getExposition().getPrice() * item.getTicket().getQuantity())
                .reduce(0.0, Double::sum);
    }
}
