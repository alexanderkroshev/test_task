package com.company;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

//TODO change public to private in some methods
@ToString
@AllArgsConstructor
public class PokerHand implements Comparable<PokerHand> {
    private String cards;

    public List<SuitCard> getSuitCardList() {
        List<SuitCard> suitCardList = new ArrayList<>();
        String[] array = this.cards.split(" ");
        if (array.length != 5)
            throw new RuntimeException("input hand size is not equail to 5");
        for ( String i : array ) {
            suitCardList.add(new SuitCard(SuitCardParse.getSuit(i), SuitCardParse.getCard(i)));
        }
        return suitCardList;
    }

    public Combination getCombination() {
        Map<Card, Integer> map = new HashMap<>();
        for ( SuitCard i : getSuitCardList() ) {
            if (!map.containsKey(i.getCard()))
                map.put(i.getCard(), 1);
            else {
                map.put(i.getCard(), map.get(i.getCard()) + 1);
            }
        }
        Collection<Integer> values = map.values();
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
                if (getSuitCardList().stream().map(SuitCard::getCard).anyMatch(x -> x == Card.ACE))
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

    public boolean theSameSuitOrNot() {
        Suit suit = getSuitCardList().get(0).getSuit();
        return getSuitCardList().stream()
                .map(SuitCard::getSuit)
                .allMatch(x -> x == suit);
    }

    public boolean sequenceOrNot() {
        List<Integer> powerList = getSuitCardList().stream()
                .map(x -> x.getCard().getPower())
                .sorted()
                .collect(Collectors.toList());
        for ( int i = 0; i < powerList.size() - 1; i++ ) {
            if (powerList.get(i) + 1 != powerList.get(i + 1))
                return false;
        }
        return true;
    }

    @Override
    public int compareTo(PokerHand o) {
        return o.getCombination().getPower()-this.getCombination().getPower() ;
    }
}
