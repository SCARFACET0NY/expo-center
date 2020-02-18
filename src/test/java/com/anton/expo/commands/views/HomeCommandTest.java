package com.anton.expo.commands.views;

import com.anton.expo.repository.entity.Hall;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HomeCommandTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    HallService hallService;
    HomeCommand homeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        homeCommand = new HomeCommand(hallService);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void homePageTest() throws Exception {
        List<Hall> halls = new ArrayList<>();
        Hall hall1 = new Hall();
        Hall hall2 = new Hall();

        halls.add(hall1);
        halls.add(hall2);

        when(hallService.getAllHalls()).thenReturn(halls);
        when(session.getAttribute("mailSuccess")).thenReturn("success");

        String[] home = homeCommand.process(request, response);

        assertEquals("index", home[0]);
        assertEquals("forward", home[1]);

        verify(request).setAttribute("halls", halls);
        verify(request).setAttribute("mailSuccess", "success");
    }
}