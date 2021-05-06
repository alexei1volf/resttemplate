package com.example.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Burger {
    private final PieceOfBread pieceOfBread;
    private final Bacon bacon;
    private final Cheese cheese;
}
