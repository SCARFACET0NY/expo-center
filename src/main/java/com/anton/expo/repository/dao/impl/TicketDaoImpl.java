package com.anton.expo.repository.dao.impl;

import com.anton.expo.exceptions.TicketException;
import com.anton.expo.repository.dao.TicketDao;
import com.anton.expo.repository.entity.Ticket;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    private static final Logger LOG = Logger.getLogger(TicketDaoImpl.class);
    private final Connection connection;

    private final String CREATE_TICKET = "INSERT INTO ticket (`date`, quantity, exposition_id, payment_id) " +
            "VALUES (?, ?, ?, ?)";
    private final String LAST_ID = "SELECT LAST_INSERT_ID()";

    public TicketDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Ticket get(long id) {
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public long save(Ticket ticket) {
        long id = -1;
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE_TICKET);
             Statement idStatement = this.connection.createStatement()) {
            statement.setDate(1, Date.valueOf(ticket.getDate()));
            statement.setInt(2, ticket.getQuantity());
            statement.setLong(3, ticket.getExpositionId());
            statement.setLong(4, ticket.getPaymentId());
            statement.execute();

            ResultSet rs = idStatement.executeQuery(LAST_ID);
            while (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("Ticket creation failed.", e);
            throw new TicketException("Can't create ticket: " + e.getMessage(), e);
        }
        return id;
    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public void delete(Ticket ticket) {

    }
}
