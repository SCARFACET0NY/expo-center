package com.anton.expo.services;

import com.anton.expo.enums.HallType;
import com.anton.expo.repository.dao.HallDao;
import com.anton.expo.repository.entity.Hall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HallServiceTest {
    @Mock
    HallDao hallDao;
    HallService hallService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        hallService = new HallService(hallDao);
    }

    @Test
    void getAllHallsTest() throws Exception {
        List<Hall> halls = new ArrayList<>();
        Hall hall1 = new Hall();
        Hall hall2 = new Hall();

        halls.add(hall1);
        halls.add(hall2);

        when(hallDao.getAll()).thenReturn(halls);

        List<Hall> returnedHalls = hallService.getAllHalls();

        assertNotNull(returnedHalls);
        assertEquals(halls, returnedHalls);

        verify(hallDao).getAll();
    }

    @Test
    void getHallByTypeTest() throws Exception {
        Hall largeHall = new Hall();
        Hall mediumHall = new Hall();
        Hall smallHall = new Hall();

        largeHall.setType(HallType.LARGE);
        mediumHall.setType(HallType.MEDIUM);
        smallHall.setType(HallType.SMALL);

        when(hallDao.getHallByType(any(HallType.class)))
                .thenReturn(largeHall).thenReturn(mediumHall).thenReturn(smallHall);

        assertEquals(largeHall, hallService.getHallByType(HallType.LARGE));
        assertEquals(mediumHall, hallService.getHallByType(HallType.MEDIUM));
        assertEquals(smallHall, hallService.getHallByType(HallType.SMALL));

        verify(hallDao, times(3)).getHallByType(any(HallType.class));
    }
}