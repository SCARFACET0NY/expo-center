package com.anton.expo.services;

import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.User;

import java.util.Map;
import java.util.Properties;

public interface EmailService {
    Properties loadProperties();

    String createEmailText(Map<String, TicketDto> cart, double total, User user);
}
