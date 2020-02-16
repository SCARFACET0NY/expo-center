package com.anton.expo.services.impl;

import com.anton.expo.enums.HallType;
import com.anton.expo.repository.dao.HallDao;
import com.anton.expo.repository.entity.Hall;
import com.anton.expo.services.HallService;

import java.util.List;

public class HallServiceImpl implements HallService {
    private HallDao hallDao;

    public HallServiceImpl(HallDao hallDao) {
        this.hallDao = hallDao;
    }

    public List<Hall> getAllHalls() {
        return hallDao.getAll();
    }

    public Hall getHallByType(HallType type) {
        return hallDao.getHallByType(type);
    }
}
