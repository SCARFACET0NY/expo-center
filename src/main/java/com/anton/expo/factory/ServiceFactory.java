package com.anton.expo.factory;

import com.anton.expo.services.*;
import com.anton.expo.services.impl.*;
import com.anton.expo.utils.UpdatableBCrypt;

public class ServiceFactory {
    private static EmailService emailService;
    private static ExpositionService expositionService;
    private static HallService hallService;
    private static PaymentService paymentService;
    private static UserService userService;

    static {
        emailService = new EmailServiceImpl();
        expositionService = new ExpositionServiceImpl(DaoFactory.getExpositionDao());
        hallService = new HallServiceImpl(DaoFactory.getHallDao());
        paymentService = new PaymentServiceImpl(DaoFactory.getExpositionDao(), DaoFactory.getPaymentDao());
        userService = new UserServiceImpl(DaoFactory.getUserDao(), DaoFactory.getPaymentDao(),
                new UpdatableBCrypt(11));
    }

    public ServiceFactory() {}

    public static EmailService getEmailService() {
        return emailService;
    }

    public static ExpositionService getExpositionService() {
        return expositionService;
    }

    public static HallService getHallService() {
        return hallService;
    }

    public static PaymentService getPaymentService() {
        return paymentService;
    }

    public static UserService getUserService() {
        return userService;
    }
}
