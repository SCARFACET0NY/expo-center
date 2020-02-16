package com.anton.expo.services.impl;

import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.User;
import com.anton.expo.services.EmailService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {
    public Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = EmailService.class.getClassLoader().getResourceAsStream("mail.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String createEmailText(Map<String, TicketDto> cart, double total, User user) {
        String cardNumber = String.valueOf(user.getCardNumber());
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);

        StringBuilder message = new StringBuilder("<h2>Tickets</h2>");
        for (TicketDto ticketDto : cart.values()) {
            message.append("<p>title: " + ticketDto.getExposition().getTitle() + ", ");
            message.append("date: " + ticketDto.getTicket().getDate() + ", ");
            message.append("quantity: " + ticketDto.getTicket().getQuantity() + ", ");
            message.append("price: " + ticketDto.getExposition().getPrice() + "</p>");
        }
        message.append("<br/>");
        message.append("<p>buyer: " + user.getFirstName() + " " + user.getLastName() + ", ");
        message.append("card: ******" + lastFourDigits + ", total: " + total + "</p>");

        return message.toString();
    }
}
