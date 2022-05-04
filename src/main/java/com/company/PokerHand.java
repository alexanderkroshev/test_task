package com.company;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class PokerHand implements Comparable<PokerHand> {
    private final String cards;

    public Combination getCombination() {
        CombinationParse combinationParse = new CombinationParse(getSuitCardSet());
        return combinationParse.getCombination();
    }

    private Set<SuitCard> getSuitCardSet() {
        return SuitCardParse.getSuitCardSet(cards);
    }

    private int secondCriteria() {
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
            Map<Card, Integer> map = CombinationUtils.getCardMap(getSuitCardSet());
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

    @Override
    public int compareTo(PokerHand o) {
        int result;
        result = o.getCombination().getPower() - this.getCombination().getPower();
        if (result == 0)
            result = o.secondCriteria() - this.secondCriteria();
        if (result == 0)
            result = o.thirdCriteria() - this.thirdCriteria();
        if (result == 0)
            result = o.fourthCriteria() - this.fourthCriteria();
        return result;
    }

    private int getCardPowerByFrequency(int frequency) {
        List<Integer> cards = new ArrayList<>();
        Map<Card, Integer> map = CombinationUtils.getCardMap(getSuitCardSet());
        for ( Map.Entry<Card, Integer> i : map.entrySet() ) {
            if (i.getValue() == frequency)
                cards.add(i.getKey().getPower());
        }
        return Collections.max(cards);
    }
}
