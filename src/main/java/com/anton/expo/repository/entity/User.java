package com.anton.expo.repository.entity;

import com.anton.expo.enums.AccountStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDateTime dateJoined;
    private long cardNumber;
    private String username;
    private String password;
    private AccountStatus accountStatus;
}
