package com.anton.expo.factory;

import com.anton.expo.services.ExpositionService;
import com.anton.expo.services.HallService;
import com.anton.expo.services.UserService;

public class ServiceFactory {
    private static HallService hallService = new HallService();
    private static ExpositionService expositionService = new ExpositionService();
    private static UserService userService = new UserService();

    public ServiceFactory() {}

    public static HallService getHallService() {
        return hallService;
    }

    public static ExpositionService getExpositionService() {
        return expositionService;
    }

    public static UserService getUserService() {
        return userService;
    }
}
