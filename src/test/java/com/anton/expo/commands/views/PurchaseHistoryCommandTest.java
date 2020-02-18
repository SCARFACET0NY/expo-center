package com.anton.expo.commands.views;

import com.anton.expo.repository.dto.Purchase;
import com.anton.expo.repository.entity.User;
import com.anton.expo.services.UserService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PurchaseHistoryCommandTest {
    private static final long ID = 1L;
    private static final int PAGE_NUMBER = 0;
    private static final int PAGES_NUMBER = 2;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    UserService userService;
    PurchaseHistoryCommand purchaseHistoryCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        purchaseHistoryCommand = new PurchaseHistoryCommand(userService);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void purchasesPageTest() throws Exception {
        User user = User.builder().id(ID).build();
        List<Purchase> purchases = new ArrayList<>();
        Purchase purchase1 = new Purchase();
        Purchase purchase2 = new Purchase();

        purchases.add(purchase1);
        purchases.add(purchase2);

        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(userService.getPurchasesPageByUserId(anyLong(), anyInt())).thenReturn(purchases);
        when(userService.getNumberOfPagesByUserId(anyLong())).thenReturn(PAGES_NUMBER);

        String[] purchasesPage = purchaseHistoryCommand.process(request, response);

        assertEquals("purchase-history", purchasesPage[0]);
        assertEquals("forward", purchasesPage[1]);
        assertEquals(user, session.getAttribute("user"));

        verify(session, times(2)).getAttribute("user");
        verify(request).setAttribute("purchases", purchases);
        verify(request).setAttribute("numOfPages", PAGES_NUMBER);
        verify(request).setAttribute("page", PAGE_NUMBER);
    }
}