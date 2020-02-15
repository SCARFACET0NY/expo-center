package com.anton.expo.services;

import com.anton.expo.enums.HallType;
import com.anton.expo.repository.dao.HallDao;
import com.anton.expo.repository.entity.Hall;

import java.util.List;

public class HallService {
    private HallDao hallDao;

    public HallService(HallDao hallDao) {
        this.hallDao = hallDao;
    }

    public List<Hall> getAllHalls() {
        return hallDao.getAll();
    }

    public Hall getHallByType(HallType type) {
        return hallDao.getHallByType(type);
    }
}
