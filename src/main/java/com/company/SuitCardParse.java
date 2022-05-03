package com.company;

public class SuitCardParse {

    public static Suit getSuit(String str) {
        if (str.length() != 2)
            throw new RuntimeException("incorrect input data for parsing suit");
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
            throw new RuntimeException("problem with parsing suit");
    }

    public static Card getCard(String str) {
        if (str.length() != 2)
            throw new RuntimeException("incorrect input data for parsing card");
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
            throw new RuntimeException("problem with parsing card");
    }
}
