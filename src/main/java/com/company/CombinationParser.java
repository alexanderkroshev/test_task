package com.company;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CombinationParser {
    private Set<SuitCard> suitCardSet;

    public Combination getCombination() {
        Collection<Integer> values = SuitCard.calcFrequencyMap(suitCardSet).values();
        if (values.contains(4))
            return Combination.FOUR_OF_A_KIND;
        if (values.contains(3))
            if (values.contains(2))
                return Combination.FULL_HOUSE;
            else
                return Combination.THREE_OF_A_KIND;
        if (values.contains(2)) {
            if (values.size() == 3)
                return Combination.TWO_PAIRS;
            else
                return Combination.PAIR;
        }
        if (isSequence()) {
            if (isTheSameSuit()) {
                if (suitCardSet.stream().map(SuitCard::getCard).anyMatch(x -> x == Card.ACE))
                    return Combination.ROYAL_FLUSH;
                else
                    return Combination.STRAIGHT_FLUSH;
            } else
                return Combination.STRAIGHT;
        }
        if (isTheSameSuit())
            return Combination.FLUSH;
        return Combination.HIGH_CARD;
    }

    private boolean isSequence() {
        List<Integer> powerList = suitCardSet.stream()
                .map(x -> x.getCard().getPower())
                .sorted()
                .collect(Collectors.toList());
        for ( int i = 0; i < powerList.size() - 1; i++ ) {
            if (powerList.get(i) + 1 != powerList.get(i + 1))
                return false;
        }
        return true;
    }

    private boolean isTheSameSuit() {
        Suit suit = suitCardSet.stream().findFirst().get().getSuit();
        return suitCardSet.stream()
                .map(SuitCard::getSuit)
                .allMatch(x -> x == suit);
    }
}
