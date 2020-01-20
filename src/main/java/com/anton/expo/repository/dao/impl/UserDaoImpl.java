package com.anton.expo.repository.dao.impl;

import com.anton.expo.enums.AccountStatus;
import com.anton.expo.exceptions.UserException;
import com.anton.expo.repository.dao.UserDao;
import com.anton.expo.repository.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(HallDaoImpl.class);
    private final Connection connection;

    private final String GET_USER = "SELECT first_name, last_name, phone, email, date_joined, card_number, username, " +
            "password, account_status FROM `user`";
    private final String CHECK_USER = "SELECT id FROM `user` WHERE username = ? and password = ?";

    private final String INSERT_USER = "INSERT INTO `user` (first_name, last_name, phone, email, date_joined, card_number, " +
            "username, password, account_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String LAST_ID = "SELECT LAST_INSERT_ID()";

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User get(long id) {
        User user = null;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_USER)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setDateJoined(rs.getTimestamp("date_joined").toLocalDateTime());
                user.setCardNumber(rs.getLong("card_number"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAccountStatus(AccountStatus.valueOf(rs.getString("account_status")));
            }
        } catch (SQLException e) {
            LOG.error("User extraction failed.", e);
            throw new UserException("Can't get user: " + e.getMessage(), e);
        }
        return user;
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

    @Override
    public long checkUsernameAndPassword(String username, String password) {
        long id = -1;
        try (PreparedStatement statement = this.connection.prepareStatement(CHECK_USER)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getLong("id");
            }
        } catch (SQLException e) {
            LOG.error("Unsuccessful user verification.", e);
            throw new UserException("Could not find such user: " + e.getMessage(), e);
        }
        return id;
    }
}
