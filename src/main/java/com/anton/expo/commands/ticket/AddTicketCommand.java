package com.anton.expo.commands.ticket;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;
import com.anton.expo.repository.dto.TicketDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
                cart.put(expositionId, ServiceFactory.getTicketService().createTicketDto(Long.parseLong(expositionId)));
            }
        }
        double total = ServiceFactory.getTicketService().getCartTotal(cart);

        session.setAttribute("cart", cart);
        session.setAttribute("total", total);

        return new String[] {origin, "redirect"};
    }
}
