package com.anton.expo.repository.dao;

import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Payment;

import java.util.List;

public interface PaymentDao extends Dao<Payment> {
    List<TicketDto> getTicketsByPaymentId(long id);
}
