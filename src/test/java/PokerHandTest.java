import com.company.Combination;
import com.company.PokerHand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PokerHandTest {

    @Test
    void HighCardTest() {
        PokerHand pokerHand = new PokerHand("2S 4H 5D JC KD");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.HIGH_CARD);
    }

    @Test
    void PairTest() {
        PokerHand pokerHand = new PokerHand("2S 2H 5D JC KD");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.PAIR);
    }

    @Test
    void TwoPairTest() {
        PokerHand pokerHand = new PokerHand("2S 2H 5D 5C KD");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.TWO_PAIRS);
    }

    @Test
    void ThreeOfKindTest() {
        PokerHand pokerHand = new PokerHand("2S 2H 2D JC KD");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.THREE_OF_A_KIND);
    }

    @Test
    void StraightTest() {
        PokerHand pokerHand = new PokerHand("2S 3H 4D 5C 6D");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.STRAIGHT);
    }

    @Test
    void FlushTest() {
        PokerHand pokerHand = new PokerHand("2S 4S 5S AS KS");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.FLUSH);
    }

    @Test
    void FullHouseTest() {
        PokerHand pokerHand = new PokerHand("2S 2H 2D 5C 5D");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.FULL_HOUSE);
    }

    @Test
    void FourOfKindTest() {
        PokerHand pokerHand = new PokerHand("2S 2H 2D 2C 5D");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.FOUR_OF_A_KIND);
    }

    @Test
    void StraightFlushTest() {
        PokerHand pokerHand = new PokerHand("2S 3S 4S 5S 6S");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.STRAIGHT_FLUSH);
    }

    @Test
    void RoyalFlushTest() {
        PokerHand pokerHand = new PokerHand("TS JS QS KS AS");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.ROYAL_FLUSH);
    }

    @Test
    void sortTest() {
        PokerHand hand1 = new PokerHand("TS JS QS KS AS");
        PokerHand hand2 = new PokerHand("2S 3S 4S 5S 6S");
        PokerHand hand3 = new PokerHand("2S 2H 2D 5C 5D");
        PokerHand hand4 = new PokerHand("2S 2H 5D 5C KD");

        List<PokerHand> hands = new ArrayList<>(Arrays.asList(hand2, hand3, hand1, hand4));
        List<PokerHand> sortedHands = new ArrayList<>(Arrays.asList(hand1, hand2, hand3, hand4));
        Collections.sort(hands);

        Assertions.assertEquals(hands, sortedHands);
    }
}
