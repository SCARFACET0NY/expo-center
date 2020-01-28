package com.anton.expo.services;

import com.anton.expo.factory.DaoFactory;
import com.anton.expo.repository.dao.PaymentDao;
import com.anton.expo.repository.entity.Payment;

import java.time.LocalDateTime;

public class PaymentService {
    private PaymentDao paymentDao = DaoFactory.getPaymentDao();

    public Payment createPayment(double total, long userId) {
        return Payment.builder().usedId(userId).date(LocalDateTime.now()).total(total).build();
    }

    public long savePayment(double total, long userId) {
        return paymentDao.save(createPayment(total, userId));
    }
}
