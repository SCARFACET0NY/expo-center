package com.anton.expo.factory;

import com.anton.expo.services.ExpositionService;
import com.anton.expo.services.HallService;

public class ServiceFactory {
    private static HallService hallService = new HallService();
    private static ExpositionService expositionService = new ExpositionService();

    public ServiceFactory() {}

    public static HallService getHallService() {
        return hallService;
    }

    public static ExpositionService getExpositionService() {
        return expositionService;
    }
}
