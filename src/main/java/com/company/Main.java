package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Throwable {

        String str ="2S";
        System.out.println(SuitCardParse.getCard(str));
        System.out.println(SuitCardParse.getSuit(str));
        List<Combination> list = new ArrayList<>();
        PokerHand pokerHand = new PokerHand("2S 3H 4C 5D 6D");
        System.out.println(pokerHand.getCombination());
        System.out.println(pokerHand.sequenceOrNot());

       // System.out.println(PokerHand.sequenceOrNot(pokerHand));
      //  System.out.println(pokerHand.sequenceOrNot(list));


/*
        PokerHand hand1 = new PokerHand("2 5 6");
        PokerHand hand2 = new PokerHand("2 5 7");
        PokerHand hand3 = new PokerHand("2 5 9");
        PokerHand hand4 = new PokerHand("2 5 3 ");

        List<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        hands.add(hand3);
        hands.add(hand4);
        Collections.sort(hands);
        hands.stream().map(PokerHand::getMaxValue).forEach(System.out::println);
        System.out.println("max power: "+hand2.getMaxValue());
*/
    }
}
