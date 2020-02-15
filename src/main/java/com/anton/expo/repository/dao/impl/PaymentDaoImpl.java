package com.anton.expo.repository.dao.impl;

import com.anton.expo.exceptions.PaymentException;
import com.anton.expo.repository.dao.PaymentDao;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.repository.entity.Payment;
import com.anton.expo.repository.entity.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    private static final Logger LOG = LogManager.getLogger(PaymentDaoImpl.class);
    private final Connection connection;

    private final String CREATE_PAYMENT = "INSERT INTO payment (total, payment_date, user_id) VALUES (?, ?, ?)";
    private final String CREATE_TICKET = "INSERT INTO ticket (`date`, quantity, exposition_id, payment_id) " +
            "VALUES (?, ?, ?, ?)";
    private final String GET_PAYMENTS_TICKETS = "SELECT `date`, quantity, title, price FROM ticket " +
            "LEFT JOIN exposition ON ticket.exposition_id = exposition.exposition_id WHERE payment_id = ?";
    private final String LAST_ID = "SELECT LAST_INSERT_ID()";

    public PaymentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Payment get(long id) {
        return null;
    }

    @Override
    public List<Payment> getAll() {
        return null;
    }

    @Override
    public long save(Payment payment) {
        return 0;
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(Payment payment) {

    }

    @Override
    public List<TicketDto> getTicketsByPaymentId(long id) {
        List<TicketDto> tickets = null;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_PAYMENTS_TICKETS)) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (tickets == null) tickets = new ArrayList<>();
                Ticket ticket = Ticket.builder()
                        .date(rs.getDate("date").toLocalDate()).quantity(rs.getInt("quantity")).build();
                Exposition exposition = Exposition.builder()
                        .title(rs.getString("title")).price(rs.getDouble("price")).build();
                TicketDto ticketDto = new TicketDto();
                ticketDto.setTicket(ticket);
                ticketDto.setExposition(exposition);

                tickets.add(ticketDto);
            }
        } catch (SQLException e) {
            LOG.error("Tickets extraction failed.", e);
            throw new PaymentException("Can't get tickets: " + e.getMessage(), e);
        }
        return tickets;
    }

    @Override
    public long savePaymentWithTickets(Payment payment, List<Ticket> tickets) {
        long id = -1;
        try (PreparedStatement paymentStatement = this.connection.prepareStatement(CREATE_PAYMENT);
             Statement idStatement = this.connection.createStatement();
             PreparedStatement ticketStatement = this.connection.prepareStatement(CREATE_TICKET)
        ) {
            connection.setAutoCommit(false);

            paymentStatement.setDouble(1, payment.getTotal());
            paymentStatement.setTimestamp(2, Timestamp.valueOf(payment.getDate()));
            paymentStatement.setLong(3, payment.getUsedId());
            paymentStatement.execute();

            ResultSet rs = idStatement.executeQuery(LAST_ID);
            while (rs.next()) {
                id = rs.getLong(1);

                for (Ticket ticket : tickets) {
                    ticket.setPaymentId(id);
                    ticketStatement.setDate(1, Date.valueOf(ticket.getDate().plusDays(1)));
                    ticketStatement.setInt(2, ticket.getQuantity());
                    ticketStatement.setLong(3, ticket.getExpositionId());
                    ticketStatement.setLong(4, ticket.getPaymentId());
                    ticketStatement.addBatch();
                }
                ticketStatement.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Transaction is being rolled back. Creation of payment with tickets failed", e);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException se) {
                LOG.error("Transaction rollback failed", se);
            }
            throw new PaymentException("Can't create payment with tickets: " + e.getMessage(), e);
        }

        return id;
    }
}
