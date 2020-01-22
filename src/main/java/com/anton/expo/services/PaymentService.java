package com.anton.expo.services;

import com.anton.expo.factory.DaoFactory;
import com.anton.expo.repository.dao.PaymentDao;
import com.anton.expo.repository.entity.Payment;

import java.time.LocalDateTime;

public class PaymentService {
    private PaymentDao paymentDao = DaoFactory.getPaymentDao();

    public Payment createPayment(double total, long userId) {
        Payment payment = new Payment();
        payment.setTotal(total);
        payment.setDate(LocalDateTime.now());
        payment.setUsedId(userId);

        return payment;
    }

    public long savePayment(double total, long userId) {
        return paymentDao.save(createPayment(total, userId));
    }
}
