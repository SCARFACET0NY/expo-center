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
class CartCommandTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    CartCommand cartCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cartCommand = new CartCommand();
    }

    @Test
    void adminPageTest() throws Exception {
        String[] adminPage = cartCommand.process(request, response);
        assertEquals("cart", adminPage[0]);
        assertEquals("forward", adminPage[1]);
    }
}