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
        Map<Card, Integer> map = new HashMap<>();
        for ( SuitCard i : getSuitCardSet() ) {
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

    public boolean theSameSuitOrNot() {
        Suit suit = getSuitCardSet().stream().findFirst().get().getSuit();
        return getSuitCardSet().stream()
                .map(SuitCard::getSuit)
                .allMatch(x -> x == suit);
    }

    public boolean sequenceOrNot() {
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

    public int secondCriteria(){
        if(this.getCombination() == Combination.HIGH_CARD)
            return getMaxCard();
        else
            return 0;
    }

    @Override
    public int compareTo(PokerHand o) {
        return o.getCombination().getPower() - this.getCombination().getPower();
    }

    public int getMaxCard(){
        return getSuitCardSet()
                .stream()
                .map(x->x.getCard().getPower())
                .max(Integer::compareTo)
                .get();
    }
}
