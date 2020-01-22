package com.anton.expo.commands.ticket;

import com.anton.expo.commands.Command;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.repository.entity.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class TicketQuantityCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String expositionId = req.getParameter("exposition_id");
        String sign = req.getParameter("sign");

        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");
        if (cart.containsKey(expositionId)) {
            Ticket ticket = cart.get(expositionId).getTicket();
            Exposition exposition = cart.get(expositionId).getExposition();
            if (sign.equals("+")) {
                ticket.setQuantity(ticket.getQuantity() + 1);
                session.setAttribute("total", (double) session.getAttribute("total") + exposition.getPrice());
            } else if (sign.equals("-")) {
                if (ticket.getQuantity() > 1) {
                    ticket.setQuantity(ticket.getQuantity() - 1);
                    session.setAttribute("total", (double) session.getAttribute("total") - exposition.getPrice());
                }
            }
        }

        return new String[] {"cart", "redirect"};
    }
}
