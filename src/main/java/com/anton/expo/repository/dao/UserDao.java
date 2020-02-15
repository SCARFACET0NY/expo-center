package com.anton.expo.repository.dao;

import com.anton.expo.repository.dto.Purchase;
import com.anton.expo.repository.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    long checkUsername(String username);

    String getPasswordForUsername(String username);

    List<Purchase> getUserPurchasesPaged(long userId, int offset, int numPerPage);

    int getNumberOfPurchasesByUserId(long userId);
}
