package com.anton.expo.services;

import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.User;

import java.util.Map;

public class EmailService {
    public String createEmailText(Map<String, TicketDto> cart, double total, User user) {
        String cardNumber = String.valueOf(user.getCardNumber());
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);

        StringBuilder message = new StringBuilder("<h2>Tickets</h2>");
        for (TicketDto ticketDto : cart.values()) {
            message.append("<p>title: " + ticketDto.getExposition().getTitle() + ", ");
            message.append("date: " + ticketDto.getTicket().getDate() + ", ");
            message.append("price: " + ticketDto.getExposition().getPrice() + "</p>");
        }
        message.append("<br/>");
        message.append("<p>buyer: " + user.getFirstName() + " " + user.getLastName() + ", ");
        message.append("card: ******" + lastFourDigits + ", total: " + total + "</p>");

        return message.toString();
    }
}
