package com.company;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PokerHand implements Comparable<PokerHand> {
    private String cards;

    public Set<SuitCard> getSuitCardSet() {
        Set<SuitCard> suitCards = new HashSet<>();
        String[] array = this.cards.split(" ");
        if (array.length != 5)
            throw new RuntimeException("amount of cards is not equal to 5");
        for ( String i : array ) {
            suitCards.add(new SuitCard(SuitCardParse.getSuit(i), SuitCardParse.getCard(i)));
        }
        if (suitCards.size() != 5)
            throw new RuntimeException("hand contain identical card");
        return suitCards;
    }

    public Combination getCombination() {
        Collection<Integer> values = getCollisionMap().values();
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
        if (sequenceOrNot()) {
            if (theSameSuitOrNot()) {
                if (getSuitCardSet().stream().map(SuitCard::getCard).anyMatch(x -> x == Card.ACE))
                    return Combination.ROYAL_FLUSH;
                else
                    return Combination.STRAIGHT_FLUSH;
            } else
                return Combination.STRAIGHT;
        }
        if (theSameSuitOrNot())
            return Combination.FLUSH;
        return Combination.HIGH_CARD;
    }

    private boolean theSameSuitOrNot() {
        Suit suit = getSuitCardSet().stream().findFirst().get().getSuit();
        return getSuitCardSet().stream()
                .map(SuitCard::getSuit)
                .allMatch(x -> x == suit);
    }

    private boolean sequenceOrNot() {
        List<Integer> powerList = getSuitCardSet().stream()
                .map(x -> x.getCard().getPower())
                .sorted()
                .collect(Collectors.toList());
        for ( int i = 0; i < powerList.size() - 1; i++ ) {
            if (powerList.get(i) + 1 != powerList.get(i + 1))
                return false;
        }
        return true;
    }

    private int secondCriteria() {
        Combination combination = this.getCombination();
        if (combination == Combination.HIGH_CARD)
            return getPowerByFrequency(1);
        if (combination == Combination.PAIR || combination == Combination.TWO_PAIRS)
            return getPowerByFrequency(2);
        if (combination == Combination.THREE_OF_A_KIND)
            return getPowerByFrequency(3);
        if (combination == Combination.FOUR_OF_A_KIND)
            return getPowerByFrequency(4);
        if (combination == Combination.FULL_HOUSE)
            return getPowerByFrequency(3);
        if (combination == Combination.STRAIGHT
                || combination == Combination.FLUSH
                || combination == Combination.STRAIGHT_FLUSH)
            return getPowerByFrequency(1);
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

    private int fourthCriteria() {
        if (this.getCombination() == Combination.TWO_PAIRS)
            return getPowerByFrequency(1);
        else
            return 0;
    }

    private int thirdCriteria() {
        Combination combination = this.getCombination();
        if (combination == Combination.PAIR)
            return getPowerByFrequency(1);
        if (combination == Combination.TWO_PAIRS) {
            List<Integer> cards = new ArrayList<>();
            Map<Card, Integer> map = getCollisionMap();
            for ( Map.Entry<Card, Integer> i : map.entrySet() ) {
                if (i.getValue() == 2)
                    cards.add(i.getKey().getPower());
            }
            return Collections.min(cards);
        } else
            return 0;
    }

    private Map<Card, Integer> getCollisionMap() {
        Map<Card, Integer> map = new HashMap<>();
        for ( SuitCard i : getSuitCardSet() ) {
            if (!map.containsKey(i.getCard()))
                map.put(i.getCard(), 1);
            else {
                map.put(i.getCard(), map.get(i.getCard()) + 1);
            }
        }
        return map;
    }

    private int getPowerByFrequency(int frequency) {
        List<Integer> cards = new ArrayList<>();
        Map<Card, Integer> map = getCollisionMap();
        for ( Map.Entry<Card, Integer> i : map.entrySet() ) {
            if (i.getValue() == frequency)
                cards.add(i.getKey().getPower());
        }
        return Collections.max(cards);
    }
}
