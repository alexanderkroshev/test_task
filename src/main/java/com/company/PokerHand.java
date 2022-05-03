package com.company;

import lombok.AllArgsConstructor;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PokerHand implements Comparable<PokerHand> {
    private String cards;

    public List<SuitCard> getSuitCardList() {
        List<SuitCard> suitCardList = new ArrayList<>();
        String[] array = this.cards.split(" ");
        for ( String i : array ) {
            suitCardList.add(new SuitCard(getSuit(i), getCard(i)));
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
        List<Integer> values = (List<Integer>) map.values();
        if (values.contains(4))
            return Combination.FOUR_OF_A_KIND;
        if (values.contains(3) && values.contains(2))
            return Combination.FULL_HOUSE;
        if (values.contains(2)) {
            if (values.size() == 3)
                return Combination.TWO_PAIRS;
            else
                return Combination.PAIR;
        }
        if (sequenceOrNot(values)) {
            if (theSameSuitOrNot()) {
                if (getSuitCardList().stream().map(SuitCard::getCard).anyMatch(x -> x == Card.ACE))
                    return Combination.ROYAL_FLUSH;
                else
                    return Combination.STRAIGHT_FLUSH;
            } else
                return Combination.STRAIGHT;
        }
        else
            return Combination.FLUSH;
    }

    public boolean theSameSuitOrNot() {
        Suit suit = getSuitCardList().get(0).getSuit();
        return getSuitCardList().stream()
                .map(SuitCard::getSuit)
                .allMatch(x -> x == suit);
    }


    public boolean sequenceOrNot(Collection<Integer> list) {
        List<Integer> powerList = list.stream()
                .sorted()
                .collect(Collectors.toList());
        for ( int i = 0; i < powerList.size() - 1; i++ ) {
            if (powerList.get(i) + 1 != powerList.get(i + 1))
                return false;
        }
        return true;
    }

    public Suit getSuit(String str) {
        char secondChar = str.charAt(1);
        if (secondChar == 'S')
            return Suit.SPADE;
        if (secondChar == 'C')
            return Suit.CLUB;
        if (secondChar == 'D')
            return Suit.DIAMOND;
        if (secondChar == 'H')
            return Suit.HEART;
        else
            throw new RuntimeException("problem with parsing value");
    }

    public Card getCard(String str) {
        char firstChar = str.charAt(0);
        if (firstChar == '2')
            return Card.TWO;
        if (firstChar == '3')
            return Card.THREE;
        if (firstChar == '4')
            return Card.FOUR;
        if (firstChar == '5')
            return Card.FIVE;
        if (firstChar == '6')
            return Card.SIX;
        if (firstChar == '7')
            return Card.SEVEN;
        if (firstChar == '8')
            return Card.EIGHT;
        if (firstChar == '9')
            return Card.NINE;
        if (firstChar == 'T')
            return Card.TEN;
        if (firstChar == 'J')
            return Card.JACK;
        if (firstChar == 'Q')
            return Card.QUEEN;
        if (firstChar == 'K')
            return Card.KING;
        if (firstChar == 'A')
            return Card.ACE;
        else
            throw new RuntimeException("problem with parsing combination");
    }

    @Override
    public int compareTo(PokerHand o) {
        return this.getCombination().getPower() + -o.getCombination().getPower();
    }
}
