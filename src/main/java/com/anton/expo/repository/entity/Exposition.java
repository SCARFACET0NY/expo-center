package com.anton.expo.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Exposition {
    private long id;
    private String title;
    private String description;
    private double price;
    private String imagePath;
    private LocalDate startDate;
    private LocalDate endDate;
}
