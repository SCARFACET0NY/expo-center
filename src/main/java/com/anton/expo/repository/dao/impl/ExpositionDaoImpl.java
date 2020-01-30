package com.anton.expo.repository.dao.impl;

import com.anton.expo.exceptions.ExpositionException;
import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.entity.Exposition;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpositionDaoImpl implements ExpositionDao {
    private static final Logger LOG = Logger.getLogger(HallDaoImpl.class);
    private final Connection connection;

    private final String CREATE_EXPOSITION = "INSERT INTO exposition (title, description, price, image_path, " +
                "start_date, end_date, hall_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String GET_EXPOSITION_BY_ID = "SELECT exposition_id, title, description, price, image_path," +
            "start_date, end_date, hall_id FROM exposition WHERE exposition_id = ?";
    private final String GET_EXPOSITIONS_FOR_HALL = "SELECT exposition_id, title, description, price, image_path," +
            "start_date, end_date, hall_id FROM exposition WHERE hall_id = ? AND end_date > ?";
    private final String SEARCH_EXPOSITIONS_BY_TITLE = "SELECT exposition_id, title, description, price, image_path," +
            "start_date, end_date, hall_id FROM exposition WHERE title LIKE ? AND end_date > ?";
    private final String UPDATE_EXPOSITION = "UPDATE exposition SET title = ?, description = ?, price = ?, " +
            "image_path = ?, start_date = ?, end_date = ?, hall_id = ? WHERE exposition_id = ?";
    private final String LAST_ID = "SELECT LAST_INSERT_ID()";

    public ExpositionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Exposition get(long id) {
        Exposition exposition = null;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_EXPOSITION_BY_ID)) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                exposition = new Exposition();

                exposition.setId(rs.getLong("exposition_id"));
                exposition.setTitle(rs.getString("title"));
                exposition.setDescription(rs.getString("description"));
                exposition.setPrice(rs.getDouble("price"));
                exposition.setImagePath(rs.getString("image_path"));
                exposition.setStartDate(rs.getDate("start_date").toLocalDate());
                exposition.setEndDate(rs.getDate("end_date").toLocalDate());
                exposition.setHallId(rs.getLong("hall_id"));
            }
        } catch (SQLException e) {
            LOG.error("Extraction of exposition by id failed.", e);
            throw new ExpositionException("Can't get exposition by id: " + e.getMessage(), e);
        }
        return exposition;
    }

    @Override
    public List<Exposition> getAll() {
        return null;
    }

    @Override
    public long save(Exposition exposition) {
        long id = -1;
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE_EXPOSITION);
             Statement idStatement = this.connection.createStatement()) {
            statement.setString(1, exposition.getTitle());
            statement.setString(2, exposition.getDescription());
            statement.setDouble(3, exposition.getPrice());
            statement.setString(4, exposition.getImagePath());
            statement.setDate(5, Date.valueOf(exposition.getStartDate().plusDays(1)));
            statement.setDate(6, Date.valueOf(exposition.getEndDate().plusDays(1)));
            statement.setLong(7, exposition.getHallId());
            statement.execute();

            ResultSet rs = idStatement.executeQuery(LAST_ID);
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("Creation of exposition failed.", e);
            throw new ExpositionException("Can't create exposition: " + e.getMessage(), e);
        }
        return id;
    }

    @Override
    public void update(Exposition exposition) {
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_EXPOSITION);) {
            statement.setString(1, exposition.getTitle());
            statement.setString(2, exposition.getDescription());
            statement.setDouble(3, exposition.getPrice());
            statement.setString(4, exposition.getImagePath());
            statement.setDate(5, Date.valueOf(exposition.getStartDate().plusDays(1)));
            statement.setDate(6, Date.valueOf(exposition.getEndDate().plusDays(1)));
            statement.setLong(7, exposition.getHallId());
            statement.setLong(8, exposition.getId());
            statement.execute();
        } catch (SQLException e) {
            LOG.error("Modification of exposition failed.", e);
            throw new ExpositionException("Can't modify exposition: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Exposition exposition) {

    }

    @Override
    public List<Exposition> getActiveExpositionsForHall(long id) {
        List<Exposition> expositions = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_EXPOSITIONS_FOR_HALL)) {
            statement.setLong(1, id);
            statement.setDate(2, Date.valueOf(LocalDate.now()));

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Exposition exposition = new Exposition();
                exposition.setId(rs.getLong("exposition_id"));
                exposition.setTitle(rs.getString("title"));
                exposition.setDescription(rs.getString("description"));
                exposition.setPrice(rs.getDouble("price"));
                exposition.setImagePath(rs.getString("image_path"));
                exposition.setStartDate(rs.getDate("start_date").toLocalDate());
                exposition.setEndDate(rs.getDate("end_date").toLocalDate());
                exposition.setHallId(rs.getLong("hall_id"));

                expositions.add(exposition);
            }
        } catch (SQLException e) {
            LOG.error("Extraction of active expositions for hall failed.", e);
            throw new ExpositionException("Can't get active expositions for hall: " + e.getMessage(), e);
        }
        return expositions;
    }

    @Override
    public List<Exposition> searchByTitle(String query) {
        List<Exposition> expositions = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SEARCH_EXPOSITIONS_BY_TITLE)) {
            statement.setString(1, "%" + query.toLowerCase() + "%");
            statement.setDate(2, Date.valueOf(LocalDate.now()));

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Exposition exposition = new Exposition();
                exposition.setId(rs.getLong("exposition_id"));
                exposition.setTitle(rs.getString("title"));
                exposition.setDescription(rs.getString("description"));
                exposition.setPrice(rs.getDouble("price"));
                exposition.setImagePath(rs.getString("image_path"));
                exposition.setStartDate(rs.getDate("start_date").toLocalDate());
                exposition.setEndDate(rs.getDate("end_date").toLocalDate());
                exposition.setHallId(rs.getLong("hall_id"));

                expositions.add(exposition);
            }
        } catch (SQLException e) {
            LOG.error("Extraction of searched expositions for hall failed.", e);
            throw new ExpositionException("Can't get searched expositions for hall: " + e.getMessage(), e);
        }
        return expositions;
    }
}
