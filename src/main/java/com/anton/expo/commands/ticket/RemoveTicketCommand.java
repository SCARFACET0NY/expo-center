package com.anton.expo.commands.ticket;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;
import com.anton.expo.repository.dto.TicketDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class RemoveTicketCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String expositionId = req.getParameter("exposition_id");
        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");

        cart.keySet().removeIf(id -> id.equals(expositionId));
        double total = ServiceFactory.getPaymentService().getCartTotal(cart);

        session.setAttribute("cart", cart);
        session.setAttribute("total", total);

        return new String[] {"cart", "redirect"};
    }
}
