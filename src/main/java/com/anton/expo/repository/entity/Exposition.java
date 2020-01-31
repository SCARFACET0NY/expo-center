package com.anton.expo.repository.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exposition {
    private long id;
    private String title;
    private String description;
    private double price;
    private String imagePath;
    private LocalDate startDate;
    private LocalDate endDate;
    private long hallId;
}
