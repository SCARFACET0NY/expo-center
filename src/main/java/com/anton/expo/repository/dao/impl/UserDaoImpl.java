package com.anton.expo.repository.dao.impl;

import com.anton.expo.enums.AccountStatus;
import com.anton.expo.exceptions.UserException;
import com.anton.expo.repository.dao.UserDao;
import com.anton.expo.repository.dto.Purchase;
import com.anton.expo.repository.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(HallDaoImpl.class);
    private final Connection connection;

    private final String CHECK_USER = "SELECT user_id FROM `user` WHERE username = ?";
    private final String CREATE_USER = "INSERT INTO `user` (first_name, last_name, phone, email, date_joined, card_number, " +
            "username, password, account_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String GET_PASSWORD = "SELECT password FROM `user` WHERE username = ?";
    private final String GET_USER = "SELECT user_id, first_name, last_name, phone, email, date_joined, card_number, username, " +
            "password, account_status FROM `user` WHERE user_id = ?";
    private final String GET_USER_PURCHASES = "SELECT card_number, payment_id, payment_date, total FROM `user` " +
            "LEFT JOIN payment ON `user`.user_id = payment.user_id " +
            "WHERE `user`.user_id = ?";
    private final String LAST_ID = "SELECT LAST_INSERT_ID()";

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User get(long id) {
        User user = null;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_USER)) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = User.builder().build();
                user.setId(rs.getLong("user_id"));
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
                PreparedStatement statement = this.connection.prepareStatement(CREATE_USER);
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
    public long checkUsername(String username) {
        long id = -1;
        try (PreparedStatement statement = this.connection.prepareStatement(CHECK_USER)) {
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getLong("user_id");
            }
        } catch (SQLException e) {
            LOG.error("Username verification failed.", e);
            throw new UserException("Could not find such username: " + e.getMessage(), e);
        }
        return id;
    }

    @Override
    public String getPasswordForUsername(String username) {
        String password = null;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_PASSWORD)) {
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            LOG.error("Unsuccessful password verification.", e);
            throw new UserException("Could not find password for such username: " + e.getMessage(), e);
        }
        return password;
    }

    @Override
    public List<Purchase> getUserPurchases(long userId) {
        List<Purchase> purchases = null;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_USER_PURCHASES)) {
            statement.setLong(1, userId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (purchases == null) purchases = new ArrayList<>();

                Purchase purchase = new Purchase();
                purchase.setPaymentId(rs.getLong("payment_id"));
                purchase.setCardNumber(rs.getLong("card_number"));
                purchase.setDate(rs.getTimestamp("payment_date").toLocalDateTime());
                purchase.setTotal(rs.getDouble("total"));

                purchases.add(purchase);
            }
        } catch (SQLException e) {
            LOG.error("Extraction of users purchases failed.", e);
            throw new UserException("Can't extract users purchases: " + e.getMessage(), e);
        }
        return purchases;
    }
}
