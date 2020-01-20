package com.anton.expo.repository.dao;

import com.anton.expo.repository.entity.User;

public interface UserDao extends Dao<User> {
    long checkUsernameAndPassword(String username, String password);
}
