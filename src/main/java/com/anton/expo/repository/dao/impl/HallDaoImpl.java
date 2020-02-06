package com.anton.expo.repository.dao.impl;

import com.anton.expo.enums.HallType;
import com.anton.expo.repository.dao.HallDao;
import com.anton.expo.repository.entity.Hall;
import com.anton.expo.exceptions.HallException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallDaoImpl implements HallDao {
    private static final Logger LOG = LogManager.getLogger(HallDaoImpl.class);
    private final Connection connection;

    private final String GET_ALL_HALLS = "SELECT hall_id, title, area, image_path, `type` FROM hall";
    private final String GET_HALL_BY_TYPE = "SELECT hall_id, title, area, image_path, `type` FROM hall WHERE `type` = ?";

    public HallDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Hall get(long id) {
        return null;
    }

    @Override
    public List<Hall> getAll() {
        List<Hall> halls = null;
        Hall hall = null;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_ALL_HALLS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (halls == null) {
                    halls = new ArrayList<>();
                }
                hall = new Hall();
                hall.setId(rs.getLong("hall_id"));
                hall.setTitle(rs.getString("title"));
                hall.setArea(rs.getDouble("area"));
                hall.setImagePath(rs.getString("image_path"));
                hall.setType(HallType.valueOf(rs.getString("type")));

                halls.add(hall);
            }
        } catch (SQLException e) {
            LOG.error("Extraction of all halls failed.", e);
            throw new HallException("Can't get all halls: " + e.getMessage(), e);
        }

        return halls;
    }

    @Override
    public Hall getHallByType(HallType type) {
        Hall hall = null;
        try (PreparedStatement statement = this.connection.prepareStatement(GET_HALL_BY_TYPE)) {
            statement.setString(1, type.toString());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                hall = new Hall();
                hall.setId(rs.getLong("hall_id"));
                hall.setTitle(rs.getString("title"));
                hall.setArea(rs.getDouble("area"));
                hall.setImagePath(rs.getString("image_path"));
                hall.setType(HallType.valueOf(rs.getString("type")));
            }
        } catch (SQLException e) {
            LOG.error("Extraction of hall by type failed.", e);
            throw new HallException("Can't get hall: " + e.getMessage(), e);
        }

        return hall;
    }

    @Override
    public long save(Hall hall) {
        return 0;
    }

    @Override
    public void update(Hall hall) {

    }

    @Override
    public void delete(Hall hall) {

    }
}
