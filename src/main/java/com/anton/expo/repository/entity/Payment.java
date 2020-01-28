package com.anton.expo.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Payment {
    private long id;
    private double total;
    private LocalDateTime date;
    private long usedId;
}
