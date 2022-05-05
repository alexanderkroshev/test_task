package com.company;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class SuitCardParser {

    public static Set<SuitCard> getSuitCardSet(String cards) {
        Set<SuitCard> suitCards = new HashSet<>();
        String[] array = cards.split(" ");
        if (array.length != 5)
            throw new RuntimeException("amount of cards is not equal to 5");
        for ( String i : array ) {
            suitCards.add(new SuitCard(getSuit(i), getCard(i)));
        }
        if (suitCards.size() != 5)
            throw new RuntimeException("hand contain identical card");
        return suitCards;
    }

    private static Suit getSuit(String cards) {
        if (cards.length() != 2)
            throw new RuntimeException("incorrect input data");
        char secondChar = cards.charAt(1);
        if (secondChar == 'S')
            return Suit.SPADE;
        if (secondChar == 'C')
            return Suit.CLUB;
        if (secondChar == 'D')
            return Suit.DIAMOND;
        if (secondChar == 'H')
            return Suit.HEART;
        else
            throw new RuntimeException("incorrect data for parsing suit");
    }

    private static Card getCard(String cards) {
        if (cards.length() != 2)
            throw new RuntimeException("incorrect input data");
        char firstChar = cards.charAt(0);
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
            throw new RuntimeException("incorrect data for parsing card");
    }
}
