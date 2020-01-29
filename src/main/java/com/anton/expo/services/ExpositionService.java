package com.anton.expo.services;

import com.anton.expo.factory.DaoFactory;
import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.entity.Exposition;

import java.util.List;

public class ExpositionService {
    private ExpositionDao expositionDao = DaoFactory.getExpositionDao();

    public List<Exposition> getActiveExpositionsForHall(long id) {
        return expositionDao.getActiveExpositionsForHall(id);
    }

    public Exposition getExpositionById(long id) {
        return expositionDao.get(id);
    }

    public List<Exposition> searchExpositionsByTitle(String query) {
        return expositionDao.searchByTitle(query);
    }
}
