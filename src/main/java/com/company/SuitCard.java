package com.company;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class SuitCard {
    private Suit suit;
    private Card card;
}
