package com.anton.expo.services;

import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Payment;

import java.util.Map;

public interface PaymentService {
    int ONE_TICKET = 1;

    TicketDto createTicketDto(long expositionId);

    Payment createPayment(double total, long userId);

    double getCartTotal(Map<String, TicketDto> cart);

    long savePayment(double total, long userId, Map<String, TicketDto> cart);
}
