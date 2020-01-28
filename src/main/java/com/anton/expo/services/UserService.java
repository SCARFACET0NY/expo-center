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
}
