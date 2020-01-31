package com.anton.expo.repository.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Purchase {
    private long paymentId;
    private long cardNumber;
    private LocalDateTime date;
    private double total;
    private List<TicketDto> tickets = new ArrayList<>();
}
