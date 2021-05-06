package com.example.baconprovider;

import lombok.Data;

@Data
public class Bacon {
    private Boolean smoked;

    public Bacon(Boolean smoked) {
        this.smoked = smoked;
    }
}
