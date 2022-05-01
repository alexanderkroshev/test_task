package com.company;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PokerHand implements Comparable<PokerHand> {

    private String cards;

    public List<Value> getCardList() {
        List<Value> cardList = new ArrayList<>();
        String[] cardArray = this.cards.split(" ");
        for ( String i : cardArray ) {
            cardList.add(getValue(i));
        }
        return cardList;
    }

    public int getMaxPower() throws RuntimeException {
        return getCardList()
                .stream()
                .mapToInt(Value::getPower)
                .max()
                .orElseThrow(() -> new RuntimeException("problem with find max power"));
    }

//    public Combination getCombination() {
//          if (getCardList().contains("2"))
//            return Combination.;
//    }

    public Suit getSuit(String str){
        char secondChar = str.charAt(1);
        if(secondChar=='S')
            return Suit.SPADE;
        if(secondChar=='C')
            return Suit.CLUB;
        if(secondChar=='D')
            return Suit.DIAMOND;
        if(secondChar=='H')
            return Suit.HEART;
        else
            throw new RuntimeException("problem with parsing value");

    }

    private Value getValue(String str) {
        char firstChar = str.charAt(0);
        if (firstChar=='2')
            return Value.TWO;
        if (firstChar=='3')
            return Value.THREE;
        if (firstChar=='4')
            return Value.FOUR;
        if (firstChar=='5')
            return Value.FIVE;
        if (firstChar=='6')
            return Value.SIX;
        if (firstChar=='7')
            return Value.SEVEN;
        if (firstChar=='8')
            return Value.EIGHT;
        if (firstChar=='9')
            return Value.NINE;
        if (firstChar=='T')
            return Value.TEN;
        if (firstChar=='J')
            return Value.JACK;
        if (firstChar=='Q')
            return Value.QUEEN;
        if (firstChar=='K')
            return Value.KING;
        if (firstChar=='A')
            return Value.ACE;
        else
            throw new RuntimeException("problem with parsing value");
    }

    @Override
    public int compareTo(PokerHand o) {
        return this.getMaxPower() - o.getMaxPower();
    }
}
