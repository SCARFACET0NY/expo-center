package com.anton.expo.commands.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminCommandTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    AdminCommand adminCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        adminCommand = new AdminCommand();
    }

    @Test
    void adminPageTest() throws Exception {
        String[] adminPage = adminCommand.process(request, response);
        assertEquals("admin", adminPage[0]);
        assertEquals("forward", adminPage[1]);
    }
}