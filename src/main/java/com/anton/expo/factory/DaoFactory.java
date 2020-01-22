package com.anton.expo.factory;

import com.anton.expo.repository.dao.*;
import com.anton.expo.repository.dao.impl.*;
import com.anton.expo.repository.db.DBCPDataSource;

public class DaoFactory {
    private static ExpositionDao expositionDao;
    private static HallDao hallDao;
    private static PaymentDao paymentDao;
    private static TicketDao ticketDao;
    private static UserDao userDao;

    static {
        expositionDao = new ExpositionDaoImpl(DBCPDataSource.getConnection());
        hallDao = new HallDaoImpl(DBCPDataSource.getConnection());
        paymentDao = new PaymentDaoImpl(DBCPDataSource.getConnection());
        ticketDao = new TicketDaoImpl(DBCPDataSource.getConnection());
        userDao = new UserDaoImpl(DBCPDataSource.getConnection());
    }

    public DaoFactory() {}

    public static ExpositionDao getExpositionDao() {
        return expositionDao;
    }

    public static HallDao getHallDao() {
        return hallDao;
    }

    public static PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public static TicketDao getTicketDao() {
        return ticketDao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }
}
