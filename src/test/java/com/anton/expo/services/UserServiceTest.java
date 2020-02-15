package com.anton.expo.services;

import com.anton.expo.enums.AccountStatus;
import com.anton.expo.repository.dao.PaymentDao;
import com.anton.expo.repository.dao.UserDao;
import com.anton.expo.repository.dto.Purchase;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.User;
import com.anton.expo.utils.UpdatableBCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static final long ID = 1L;
    private static final int NUMBER_OF_PURCHASES = 20;
    private static final int PAGE_NUMBER = 1;
    private static  final String FIRST_NAME = "Anton";
    private static  final String USERNAME = "username";
    private static  final String PASSWORD = "password";
    @Mock
    UserDao userDao;
    @Mock
    PaymentDao paymentDao;
    @Mock
    UpdatableBCrypt updatableBCrypt;
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userDao, paymentDao, updatableBCrypt);
    }

    @Test
    void registerUserTest() throws Exception {
        when(userDao.save(any())).thenReturn(ID);

        long id = userService.registerUser(null, null, null, null,
                LocalDateTime.now(), 0L, null, null, AccountStatus.CUSTOMER);

        assertEquals(ID, id);
        verify(userDao).save(any());
    }

    @Test
    void getUserByIdTest() throws Exception {
        User user = User.builder().id(ID).firstName(FIRST_NAME).build();
        when(userDao.get(anyLong())).thenReturn(user);

        User returnedUser = userService.getUserById(ID);

        assertNotNull(returnedUser);
        assertEquals(user.getId(), returnedUser.getId());
        assertEquals(user.getFirstName(), returnedUser.getFirstName());

        verify(userDao).get(anyLong());
    }

    @Test
    void getUserByUserNameTest() throws Exception {
        User user = User.builder().id(ID).firstName(FIRST_NAME).build();
        when(userDao.checkUsername(anyString())).thenReturn(ID);
        when(userDao.get(anyLong())).thenReturn(user);

        User returnedUser = userService.getUserByUsername(FIRST_NAME);

        assertNotNull(returnedUser);
        assertEquals(user.getId(), returnedUser.getId());
        assertEquals(user.getFirstName(), returnedUser.getFirstName());

        verify(userDao).get(anyLong());
        verify(userDao).checkUsername(anyString());
    }

    @Test
    void verifyUserTest() throws Exception {
        when(userDao.checkUsername(USERNAME)).thenReturn(ID);
        when(userDao.getPasswordForUsername(USERNAME)).thenReturn(PASSWORD);
        when(updatableBCrypt.verifyHash(PASSWORD, PASSWORD)).thenReturn(true);

        assertTrue(userService.verifyUser(USERNAME, PASSWORD));
        assertFalse(userService.verifyUser(USERNAME, USERNAME));
        assertFalse(userService.verifyUser(PASSWORD, PASSWORD));

        verify(userDao, times(3)).checkUsername(anyString());
        verify(userDao, times(2)).getPasswordForUsername(anyString());
        verify(updatableBCrypt, times(2)).verifyHash(anyString(), anyString());
    }

    @Test
    void getPurchasesPageByUserIdTest() throws Exception {
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase());
        purchases.add(new Purchase());

        List<TicketDto> tickets = new ArrayList<>();
        tickets.add(new TicketDto());
        tickets.add(new TicketDto());

        when(userDao.getUserPurchasesPaged(ID,
                PAGE_NUMBER * UserService.ROWS_PER_PAGE, UserService.ROWS_PER_PAGE)).thenReturn(purchases);
        when(paymentDao.getTicketsByPaymentId(anyLong())).thenReturn(tickets);

        List<Purchase> returnedPurchases = userService.getPurchasesPageByUserId(ID, PAGE_NUMBER);

        assertEquals(purchases.size(), returnedPurchases.size());
        assertEquals(purchases, returnedPurchases);
        assertEquals(tickets.size(), returnedPurchases.get(0).getTickets().size());
        assertEquals(tickets.get(1), returnedPurchases.get(1).getTickets().get(1));

        verify(userDao).getUserPurchasesPaged(anyLong(), anyInt(), anyInt());
        verify(paymentDao, times(purchases.size())).getTicketsByPaymentId(anyLong());
    }

    @Test
    void getNumberOfPagesByUserIdTest() throws Exception {
        when(userDao.getNumberOfPurchasesByUserId(anyLong())).thenReturn(NUMBER_OF_PURCHASES);

        assertEquals(NUMBER_OF_PURCHASES / UserService.ROWS_PER_PAGE, userService.getNumberOfPagesByUserId(ID));
        verify(userDao).getNumberOfPurchasesByUserId(anyLong());
    }
}