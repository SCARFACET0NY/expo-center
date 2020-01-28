package com.anton.expo.repository.entity;

import com.anton.expo.enums.HallType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Hall {
    private long id;
    private String title;
    private double area;
    private String imagePath;
    private HallType type;
}
