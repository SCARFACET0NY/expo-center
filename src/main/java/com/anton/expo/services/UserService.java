package com.anton.expo.services;

import com.anton.expo.enums.AccountStatus;
import com.anton.expo.factory.DaoFactory;
import com.anton.expo.repository.dao.UserDao;
import com.anton.expo.repository.entity.User;
import com.anton.expo.utils.UpdatableBCrypt;

import java.time.LocalDateTime;

public class UserService {
    private UserDao userDao = DaoFactory.getUserDao();
    private UpdatableBCrypt bCrypt = new UpdatableBCrypt(11);

    public long registerUser(String firstName, String lastName, String phone, String email, LocalDateTime dateJoined,
                             long cardNumber, String userName, String password, AccountStatus status) {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setDateJoined(dateJoined);
        user.setCardNumber(cardNumber);
        user.setUsername(userName);
        user.setPassword(bCrypt.hash(password));
        user.setAccountStatus(AccountStatus.CUSTOMER);

        return userDao.save(user);
    }
}
