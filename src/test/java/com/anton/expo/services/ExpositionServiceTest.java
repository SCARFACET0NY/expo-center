package com.anton.expo.services;

import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.services.impl.ExpositionServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpositionServiceTest {
    private static final long ID = 1L;
    private static final String TITLE = "exposition 1";
    private static final List<Exposition> expositions = new ArrayList<>();
    @Mock
    ExpositionDao expositionDao;
    ExpositionService expositionService;

    @BeforeAll
    static void before() {
        Exposition exposition1 = Exposition.builder().build();
        Exposition exposition2 = Exposition.builder().build();

        expositions.add(exposition1);
        expositions.add(exposition2);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        expositionService = new ExpositionServiceImpl(expositionDao);
    }

    @Test
    void createExpositionTest() throws Exception {
        Exposition exposition = expositionService.createExposition(TITLE, null, 0, null,
                LocalDate.now(), LocalDate.now().plusDays(1), 0);

        assertNotNull(exposition);
        assertEquals(TITLE, exposition.getTitle());
        assertEquals(LocalDate.now(), exposition.getStartDate());
        assertEquals(LocalDate.now().plusDays(1), exposition.getEndDate());
    }

    @Test
    void getAllActiveExpositionsTest() throws Exception {
        when(expositionDao.getAllActiveExpositions()).thenReturn(expositions);

        List<Exposition> returnedExposition = expositionService.getAllActiveExpositions();

        assertNotNull(returnedExposition);
        assertEquals(expositions, returnedExposition);

        verify(expositionDao).getAllActiveExpositions();
    }

    @Test
    void getActiveExpositionsForHallTest() throws Exception {
        when(expositionDao.getActiveExpositionsForHall(anyLong())).thenReturn(expositions);

        List<Exposition> returnedExposition = expositionService.getActiveExpositionsForHall(ID);

        assertNotNull(returnedExposition);
        assertEquals(expositions, returnedExposition);

        verify(expositionDao).getActiveExpositionsForHall(anyLong());
    }

    @Test
    void getExpositionByIdTest() throws Exception {
        Exposition exposition = Exposition.builder().id(ID).build();
        when(expositionDao.get(anyLong())).thenReturn(exposition);

        Exposition returnedExposition = expositionService.getExpositionById(ID);

        assertNotNull(returnedExposition);
        assertEquals(exposition, returnedExposition);
        assertEquals(exposition.getId(), returnedExposition.getId());

        verify(expositionDao).get(anyLong());
    }

    @Test
    void saveExpositionTest() throws Exception {
        when(expositionDao.save(any(Exposition.class))).thenReturn(ID);

        assertEquals(ID, expositionService.saveExposition(new Exposition()));

        verify(expositionDao).save(any(Exposition.class));
    }

    @Test
    void updateExpositionTest() throws Exception {
        expositionService.updateExposition(new Exposition());

        verify(expositionDao).update(any(Exposition.class));
    }

    @Test
    void searchExpositionsByTitleTest() throws Exception {
        when(expositionDao.searchByTitle(anyString())).thenReturn(expositions);

        assertEquals(expositions, expositionService.searchExpositionsByTitle(TITLE));

        verify(expositionDao).searchByTitle(anyString());
    }
}