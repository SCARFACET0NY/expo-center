package com.anton.expo.factory;

import com.anton.expo.services.ExpositionService;
import com.anton.expo.services.HallService;
import com.anton.expo.services.TicketService;
import com.anton.expo.services.UserService;

public class ServiceFactory {
    private static ExpositionService expositionService = new ExpositionService();
    private static HallService hallService = new HallService();
    private static TicketService ticketService = new TicketService();
    private static UserService userService = new UserService();

    public ServiceFactory() {}

    public static ExpositionService getExpositionService() {
        return expositionService;
    }

    public static HallService getHallService() {
        return hallService;
    }

    public static TicketService getTicketService() {
        return ticketService;
    }

    public static UserService getUserService() {
        return userService;
    }
}
