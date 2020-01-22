package com.anton.expo.services;

import com.anton.expo.factory.DaoFactory;
import com.anton.expo.repository.dao.PaymentDao;
import com.anton.expo.repository.entity.Payment;

import java.time.LocalDateTime;

public class PaymentService {
    private PaymentDao paymentDao = DaoFactory.getPaymentDao();

    public long createPayment(double total, LocalDateTime date, long userId) {
        Payment payment = new Payment();
        payment.setTotal(total);
        payment.setDate(date);
        payment.setUsedId(userId);

        return paymentDao.save(payment);
    }
}
