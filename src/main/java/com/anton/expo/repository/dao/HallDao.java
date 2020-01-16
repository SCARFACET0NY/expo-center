package com.anton.expo.repository.dao;

import com.anton.expo.enums.HallType;
import com.anton.expo.repository.entity.Hall;

public interface HallDao extends Dao<Hall> {
    Hall getHallByType(HallType type);
}
