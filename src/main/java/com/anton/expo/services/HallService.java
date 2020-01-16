package com.anton.expo.services;

import com.anton.expo.enums.HallType;
import com.anton.expo.repository.dao.HallDao;
import com.anton.expo.repository.entity.Hall;
import com.anton.expo.factory.DaoFactory;

import java.util.List;

public class HallService {
    private HallDao hallDao = DaoFactory.getHallDao();

    public List<Hall> getAllHalls() {
        return hallDao.getAll();
    }

    public Hall getHallByType(HallType type) {
        return hallDao.getHallByType(type);
    }
}
