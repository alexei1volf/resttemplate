package com.example.bread;

import lombok.Data;

@Data
public class PieceOfBread {
    private Integer width;

    public PieceOfBread(Integer width) {
        this.width = width;
    }
}
