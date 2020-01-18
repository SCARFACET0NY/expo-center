package com.anton.expo.repository.dao;

import com.anton.expo.repository.entity.Exposition;

import java.util.List;

public interface ExpositionDao extends Dao<Exposition> {
    List<Exposition> getActiveExpositionsForHall(long id);
}
