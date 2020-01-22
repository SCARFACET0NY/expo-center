package com.anton.expo.commands.ticket;

import com.anton.expo.commands.Command;
import com.anton.expo.repository.dto.TicketDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class SetTicketDateCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String expositionId = req.getParameter("exposition_id");
        String ticketDate = req.getParameter("ticket_date");

        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");
        if (cart.containsKey(expositionId)) {
            cart.get(expositionId).getTicket().setDate(LocalDate.parse(ticketDate));
        }

        session.setAttribute("cart", cart);

        return new String[] {"cart", "redirect"};
    }
}
