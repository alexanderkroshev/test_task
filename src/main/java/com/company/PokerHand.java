package com.company;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class PokerHand implements Comparable<PokerHand> {
    private final String cards;

    public Combination getCombination() {
        CombinationParser combinationParser = new CombinationParser(getSuitCardSet());
        return combinationParser.getCombination();
    }

    private Set<SuitCard> getSuitCardSet() {
        return SuitCardParser.getSuitCardSet(cards);
    }

    @Override
    public int compareTo(PokerHand o) {
        CombinationCriteria thisCriteria = new CombinationCriteria(this.getSuitCardSet());
        CombinationCriteria oCriteria = new CombinationCriteria(o.getSuitCardSet());
        List<Integer> thisList = thisCriteria.criteriaList();
        List<Integer> oList = oCriteria.criteriaList();
        int result = 0;
        for ( int i = 0; i < thisList.size(); i++ ) {
            result = oList.get(i) - thisList.get(i);
            if (result != 0)
                break;
        }
        return result;
    }
}
