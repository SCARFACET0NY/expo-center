package com.anton.expo.factory;

import com.anton.expo.repository.dao.HallDao;
import com.anton.expo.repository.dao.HallDaoImpl;
import com.anton.expo.repository.db.DBCPDataSource;

public class DaoFactory {
    private static HallDao hallDao;

    static {
        hallDao = new HallDaoImpl(DBCPDataSource.getConnection());
    }

    public DaoFactory() {}

    public static HallDao getHallDao() {
        return hallDao;
    }
}
