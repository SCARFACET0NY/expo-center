package com.anton.expo.factory;

import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.dao.ExpositionDaoImpl;
import com.anton.expo.repository.dao.HallDao;
import com.anton.expo.repository.dao.HallDaoImpl;
import com.anton.expo.repository.db.DBCPDataSource;

public class DaoFactory {
    private static HallDao hallDao;
    private static ExpositionDao expositionDao;

    static {
        hallDao = new HallDaoImpl(DBCPDataSource.getConnection());
        expositionDao = new ExpositionDaoImpl(DBCPDataSource.getConnection());
    }

    public DaoFactory() {}

    public static HallDao getHallDao() {
        return hallDao;
    }

    public static ExpositionDao getExpositionDao() {
        return expositionDao;
    }
}
