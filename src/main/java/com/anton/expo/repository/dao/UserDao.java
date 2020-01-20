package com.anton.expo.repository.dao;

import com.anton.expo.repository.entity.User;

public interface UserDao extends Dao<User> {
    long checkUsername(String username);

    String getPasswordForUsername(String username);
}
