package com.example.cheeseprovider;

import lombok.Data;

@Data
public class Cheese {
    private Integer holesNumber;

    public Cheese(Integer holesNumber) {
        this.holesNumber = holesNumber;
    }
}
