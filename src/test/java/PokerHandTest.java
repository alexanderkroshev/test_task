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
        PokerHand pokerHand = new PokerHand("JS TS QS KS AS");
        Assertions.assertEquals(pokerHand.getCombination(), Combination.ROYAL_FLUSH);
    }

    @Test
    void sortTest() {
        PokerHand hand1 = new PokerHand("3S 3D 5C 4S AS");//pair(lowest)
        PokerHand hand2 = new PokerHand("2S 2D 3D 3S KS");//2 pair
        PokerHand hand3 = new PokerHand("2S 2D 3D 3S AS");//2 pair
        PokerHand hand4 = new PokerHand("2S 2D 4D 4S AS");//2 pair
        PokerHand hand5 = new PokerHand("3S 3D 4D 4S AS");//2 pair
        PokerHand hand6 = new PokerHand("3S 3D 3C 4S AS");//3 of kind
        PokerHand hand7 = new PokerHand("3S 3D 3C 4S 4H");//full house (highest)

        List<PokerHand> hands = new ArrayList<>(Arrays.asList(hand2, hand1, hand6, hand4, hand5, hand3, hand7));
        List<PokerHand> expectList = new ArrayList<>(Arrays.asList(hand7, hand6, hand5, hand4, hand3, hand2, hand1));
        Collections.sort(hands);
        Assertions.assertEquals(hands, expectList);
    }
}
