package com.anton.expo.factory;

import com.anton.expo.dao.HallDao;
import com.anton.expo.dao.HallDaoImpl;
import com.anton.expo.db.DBCPDataSource;

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
