package com.anton.expo.factory;

import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.dao.UserDao;
import com.anton.expo.repository.dao.impl.ExpositionDaoImpl;
import com.anton.expo.repository.dao.HallDao;
import com.anton.expo.repository.dao.impl.HallDaoImpl;
import com.anton.expo.repository.dao.impl.UserDaoImpl;
import com.anton.expo.repository.db.DBCPDataSource;

public class DaoFactory {
    private static HallDao hallDao;
    private static ExpositionDao expositionDao;
    private static UserDao userDao;

    static {
        hallDao = new HallDaoImpl(DBCPDataSource.getConnection());
        expositionDao = new ExpositionDaoImpl(DBCPDataSource.getConnection());
        userDao = new UserDaoImpl(DBCPDataSource.getConnection());
    }

    public DaoFactory() {}

    public static HallDao getHallDao() {
        return hallDao;
    }

    public static ExpositionDao getExpositionDao() {
        return expositionDao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }
}
