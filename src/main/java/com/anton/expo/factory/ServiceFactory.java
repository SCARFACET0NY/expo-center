package com.anton.expo.factory;

import com.anton.expo.services.HallService;

public class ServiceFactory {
    private static HallService hallService = new HallService();

    public ServiceFactory() {}

    public static HallService getHallService() {
        return hallService;
    }
}
