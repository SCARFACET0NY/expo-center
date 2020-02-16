package com.anton.expo.services;

import com.anton.expo.enums.HallType;
import com.anton.expo.repository.entity.Hall;

import java.util.List;

public interface HallService {
    List<Hall> getAllHalls();

    Hall getHallByType(HallType type);
}
