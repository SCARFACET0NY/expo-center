package com.anton.expo.factory;

import com.anton.expo.services.*;
import com.anton.expo.utils.UpdatableBCrypt;

public class ServiceFactory {
    private static EmailService emailService = new EmailService();
    private static ExpositionService expositionService = new ExpositionService(DaoFactory.getExpositionDao());
    private static HallService hallService = new HallService(DaoFactory.getHallDao());
    private static PaymentService paymentService = new PaymentService(
            DaoFactory.getExpositionDao(), DaoFactory.getPaymentDao());
    private static UserService userService = new UserService(
            DaoFactory.getUserDao(), DaoFactory.getPaymentDao(), new UpdatableBCrypt(11));

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
