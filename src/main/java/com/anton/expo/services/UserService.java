package com.anton.expo.services;

import com.anton.expo.enums.AccountStatus;
import com.anton.expo.repository.dto.Purchase;
import com.anton.expo.repository.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    int ROWS_PER_PAGE = 10;

    long registerUser(String firstName, String lastName, String phone, String email, LocalDateTime dateJoined,
                             long cardNumber, String userName, String password, AccountStatus status);

    User getUserById(long id);

    User getUserByUsername(String username);

    boolean verifyUser(String username, String password);

    List<Purchase> getPurchasesPageByUserId(long id, int pageNumber);

    int getNumberOfPagesByUserId(long id);
}
