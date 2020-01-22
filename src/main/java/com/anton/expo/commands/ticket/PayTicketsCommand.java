package com.anton.expo.commands.ticket;

import com.anton.expo.commands.Command;
import com.anton.expo.factory.ServiceFactory;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class PayTicketsCommand implements Command {
    @Override
    public String[] process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Map<String, TicketDto> cart = (Map<String, TicketDto>) session.getAttribute("cart");

        if (!cart.isEmpty()) {
            double total = ServiceFactory.getTicketService().getCartTotal(cart);
            long userId = ((User)session.getAttribute("user")).getId();
            long paymentId = ServiceFactory.getPaymentService().savePayment(total, userId);

            cart.values().forEach(ticketDto -> {
                ServiceFactory.getTicketService().saveTicket(ticketDto.getTicket(), paymentId);
            });
            cart.clear();

            session.setAttribute("cart", null);
            session.setAttribute("total", null);
        }

        return new String[] {"", "redirect"};
    }
}
