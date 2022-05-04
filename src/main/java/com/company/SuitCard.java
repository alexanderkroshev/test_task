package com.company;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class SuitCard {
    private Suit suit;
    private Card card;

    public static Map<Card, Integer> calcFrequencyMap(Set<SuitCard> suitCardSet ) {//TODO
        Map<Card, Integer> map = new HashMap<>();
        for ( SuitCard i : suitCardSet ) {
            if (!map.containsKey(i.getCard()))
                map.put(i.getCard(), 1);
            else {
                map.put(i.getCard(), map.get(i.getCard()) + 1);
            }
        }
        return map;
    }
}
