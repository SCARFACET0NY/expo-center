package com.anton.expo.commands.views.halls;

import com.anton.expo.enums.HallType;
import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.repository.entity.Hall;
import com.anton.expo.services.ExpositionService;
import com.anton.expo.services.HallService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MediumHallCommandTest {
    private static final String URI = "/mediumHall";
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    ExpositionService expositionService;
    @Mock
    HallService hallService;
    MediumHallCommand mediumHallCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mediumHallCommand = new MediumHallCommand(expositionService, hallService);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void mediumHallPageTest() throws Exception {
        Hall hall = new Hall();
        hall.setType(HallType.MEDIUM);

        List<Exposition> expositions = new ArrayList<>();
        Exposition exposition1 = Exposition.builder().build();
        Exposition exposition2 = Exposition.builder().build();

        expositions.add(exposition1);
        expositions.add(exposition2);

        when(hallService.getHallByType(any(HallType.class))).thenReturn(hall);
        when(expositionService.getActiveExpositionsForHall(anyLong())).thenReturn(expositions);
        when(request.getRequestURI()).thenReturn(URI);

        String[] hallPage = mediumHallCommand.process(request, response);

        assertEquals("hall", hallPage[0]);
        assertEquals("forward", hallPage[1]);

        verify(request).setAttribute("hall", hall);
        verify(request).setAttribute("expositions", expositions);
        verify(session).setAttribute("origin", URI.substring(1));
    }
}