package com.anton.expo.repository.dao.impl;

import com.anton.expo.exceptions.UserException;
import com.anton.expo.repository.dao.UserDao;
import com.anton.expo.repository.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(HallDaoImpl.class);
    private final Connection connection;

    private final String INSERT_USER = "INSERT INTO user (first_name, last_name, phone, email, date_joined, card_number, " +
            "username, password, account_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String LAST_ID = "SELECT LAST_INSERT_ID()";

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public long save(User user) {
        long id = -1;
        try (
                PreparedStatement statement = this.connection.prepareStatement(INSERT_USER);
                Statement idStatement = this.connection.createStatement()
        ) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setTimestamp(5, Timestamp.valueOf(user.getDateJoined()));
            statement.setLong(6, user.getCardNumber());
            statement.setString(7, user.getUsername());
            statement.setString(8, user.getPassword());
            statement.setString(9, user.getAccountStatus().toString());
            statement.execute();

            ResultSet rs = idStatement.executeQuery(LAST_ID);
            while (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("New user creation failed.", e);
            throw new UserException("User creation failed: " + e.getMessage(), e);
        }
        return id;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
