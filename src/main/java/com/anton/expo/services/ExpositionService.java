package com.anton.expo.services;

import com.anton.expo.factory.DaoFactory;
import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.entity.Exposition;

import java.time.LocalDate;
import java.util.List;

public class ExpositionService {
    private ExpositionDao expositionDao = DaoFactory.getExpositionDao();

    public Exposition createExposition(String title, String description, double price, String imagePath,
                                       LocalDate startDate, LocalDate endDate, long hallId) {

        return Exposition.builder().title(title).description(description).price(price).imagePath(imagePath)
                .startDate(startDate).endDate(endDate).hallId(hallId).build();
    }

    public List<Exposition> getAllActiveExpositions() {
        return expositionDao.getAllActiveExpositions();
    }

    public List<Exposition> getActiveExpositionsForHall(long id) {
        return expositionDao.getActiveExpositionsForHall(id);
    }

    public Exposition getExpositionById(long id) {
        return expositionDao.get(id);
    }

    public long saveExposition(Exposition exposition) {
        return expositionDao.save(exposition);
    }

    public void updateExposition(Exposition exposition) {
        expositionDao.update(exposition);
    }

    public List<Exposition> searchExpositionsByTitle(String query) {
        return expositionDao.searchByTitle(query);
    }
}
