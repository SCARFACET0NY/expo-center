package com.anton.expo.services;

import com.anton.expo.enums.AccountStatus;
import com.anton.expo.repository.dao.PaymentDao;
import com.anton.expo.repository.dao.UserDao;
import com.anton.expo.repository.dto.Purchase;
import com.anton.expo.repository.entity.User;
import com.anton.expo.utils.UpdatableBCrypt;

import java.time.LocalDateTime;
import java.util.List;

public class UserService {
    public static final int ROWS_PER_PAGE = 10;
    private UserDao userDao;
    private PaymentDao paymentDao;
    private UpdatableBCrypt bCrypt;

    public UserService(UserDao userDao, PaymentDao paymentDao, UpdatableBCrypt bCrypt) {
        this.userDao = userDao;
        this.paymentDao = paymentDao;
        this.bCrypt = bCrypt;
    }

    public long registerUser(String firstName, String lastName, String phone, String email, LocalDateTime dateJoined,
                             long cardNumber, String userName, String password, AccountStatus status) {

        User user = User.builder().firstName(firstName).lastName(lastName).phone(phone).email(email)
                .dateJoined(dateJoined).cardNumber(cardNumber).username(userName).password(bCrypt.hash(password))
                .accountStatus(status).build();

        return userDao.save(user);
    }

    public User getUserById(long id) {
        return userDao.get(id);
    }

    public User getUserByUsername(String username) {
        return getUserById(userDao.checkUsername(username));
    }

    public boolean verifyUser(String username, String password) {
        return userDao.checkUsername(username) > 0 && bCrypt.verifyHash(password, userDao.getPasswordForUsername(username));
    }

    public List<Purchase> getPurchasesPageByUserId(long id, int pageNumber) {
        List<Purchase> purchases = userDao.getUserPurchasesPaged(id, ROWS_PER_PAGE * pageNumber, ROWS_PER_PAGE);
        purchases.forEach(purchase -> {
            purchase.setTickets(paymentDao.getTicketsByPaymentId(purchase.getPaymentId()));
        });

        return purchases;
    }

    public int getNumberOfPagesByUserId(long id) {
        return userDao.getNumberOfPurchasesByUserId(id) / ROWS_PER_PAGE;
    }
}
