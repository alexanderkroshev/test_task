package com.company;

import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class CombinationCriteria {
    private Set<SuitCard> suitCardSet;

    public List<Integer> criteriaList() {
        return new ArrayList<>((Arrays.asList(
                getCombination().getPower(),
                secondCriteria(),
                thirdCriteria(),
                fourthCriteria())
        ));
    }

    private Combination getCombination() {
        CombinationParser combinationParser = new CombinationParser(suitCardSet);
        return combinationParser.getCombination();
    }

    private  int secondCriteria() {
        Combination combination = getCombination();
        if (combination == Combination.HIGH_CARD)
            return getCardPowerByFrequency(1);
        if (combination == Combination.PAIR || combination == Combination.TWO_PAIRS)
            return getCardPowerByFrequency(2);
        if (combination == Combination.THREE_OF_A_KIND)
            return getCardPowerByFrequency(3);
        if (combination == Combination.FOUR_OF_A_KIND)
            return getCardPowerByFrequency(4);
        if (combination == Combination.FULL_HOUSE)
            return getCardPowerByFrequency(3);
        if (
                combination == Combination.STRAIGHT
                        || combination == Combination.FLUSH
                        || combination == Combination.STRAIGHT_FLUSH
        )
            return getCardPowerByFrequency(1);
        else
            return 0;
    }

    private int thirdCriteria() {
        Combination combination = getCombination();
        if (combination == Combination.PAIR)
            return getCardPowerByFrequency(1);
        if (combination == Combination.TWO_PAIRS) {
            List<Integer> cards = new ArrayList<>();
            Map<Card, Integer> map = SuitCard.calcFrequencyMap(suitCardSet);
            for ( Map.Entry<Card, Integer> i : map.entrySet() ) {
                if (i.getValue() == 2)
                    cards.add(i.getKey().getPower());
            }
            return Collections.min(cards);
        } else
            return 0;
    }

    private int fourthCriteria() {
        if (getCombination() == Combination.TWO_PAIRS)
            return getCardPowerByFrequency(1);
        else
            return 0;
    }

    private int getCardPowerByFrequency(int frequency) {
        List<Integer> cards = new ArrayList<>();
        Map<Card, Integer> map = SuitCard.calcFrequencyMap(suitCardSet);
        for ( Map.Entry<Card, Integer> i : map.entrySet() ) {
            if (i.getValue() == frequency)
                cards.add(i.getKey().getPower());
        }
        return Collections.max(cards);
    }
}
