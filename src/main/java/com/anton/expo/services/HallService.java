package com.anton.expo.services;

import com.anton.expo.dao.HallDao;
import com.anton.expo.entity.Hall;
import com.anton.expo.factory.DaoFactory;

import java.util.List;

public class HallService {
    private HallDao hallDao;

    public List<Hall> getAllHalls() {
        return hallDao.getAll();
    }
}
