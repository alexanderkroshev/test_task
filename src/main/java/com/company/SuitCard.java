package com.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
public class SuitCard {
    private Suit suit;
    private  Card card;
}
