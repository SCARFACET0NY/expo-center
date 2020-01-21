package com.anton.expo.commands.ticket;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AddTicketCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String origin = (String) session.getAttribute("origin");
        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");
        if (cart == null) cart = new HashMap<>();
        String expositionId = req.getParameter("exposition_id");

        if (expositionId != null) {
            if (!cart.containsKey(expositionId)) {
                TicketDto ticketDto = new TicketDto();
                Ticket ticket = new Ticket();
                ticket.setQuantity(1);
                ticket.setDate(LocalDate.now());

                ticketDto.setTicket(ticket);
                ticketDto.setExposition(ServiceFactory.getExpositionService().getExpositionById(Long.parseLong(expositionId)));

                cart.put(expositionId, ticketDto);
            }
        }

        session.setAttribute("cart", cart);

        return new String[] {origin, "redirect"};
    }
}
