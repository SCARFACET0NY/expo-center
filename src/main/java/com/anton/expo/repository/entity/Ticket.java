package com.anton.expo.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Ticket {
    private long id;
    private LocalDate date;
    private int quantity;
    private long expositionId;
    private long paymentId;
}
