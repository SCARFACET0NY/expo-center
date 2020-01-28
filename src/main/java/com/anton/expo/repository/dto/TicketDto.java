package com.anton.expo.repository.dto;

import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.repository.entity.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketDto {
    private Ticket ticket;
    private Exposition exposition;
}
