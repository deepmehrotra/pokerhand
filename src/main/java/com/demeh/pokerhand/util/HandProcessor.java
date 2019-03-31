package com.demeh.pokerhand.util;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HandProcessor {

    public static String getHandType(Card card[]){
        if(isRoyalFlush(card)) return HandTypes.ROYAL_FLUSH.getHandType();
        if(isStraightFlush(card)) return HandTypes.STRAIGHT_FLUSH.getHandType();
        if(isFourOfaKind(card)) return HandTypes.FOUR_OF_A_KIND.getHandType();
        if(isFullHouse(card)) return HandTypes.FULL_HOUSE.getHandType();
        if(isFlush(card)) return HandTypes.FLUSH.getHandType();
        if(isStraight(card)) return HandTypes.STRAIGHT.getHandType();
        if(isThreeOfaKind(card)) return HandTypes.THREE_OF_A_KIND.getHandType();
        if(isTwoPair(card)) return HandTypes.TWO_PAIR.getHandType();
        if(isOnePair(card)) return HandTypes.ONE_PAIR.getHandType();
        return HandTypes.HIGH_CARD.getHandType();
    }

    private static boolean isSameSuite(Card card[]){
       return Arrays.stream(card).map(x->new Character(x.getSuite())).distinct().count()==1;
    }

    private static boolean isContinousRankCards(Card card[]){
       for(int i=0;i<card.length-1;i++){
           int diff=Constants.rankMap.get(card[i+1].getValue())-Constants.rankMap.get(card[i].getValue());
           if(diff!=1){
               return false;
           }
       }
       return true;
    }

    private static boolean isRoyalFlush(Card card[]){
        return isSameSuite(card)&&isContinousRankCards(card)&&card[4].isAce();
    }

    private static boolean isStraightFlush(Card card[]){
        return isSameSuite(card)&&(is5HighFlush(card)||isContinousRankCards(card));
    }

    private static boolean is5HighFlush(Card card[]){
        return card[4].isAce()&&card[3].getValue()=='5'&&card[2].getValue()=='4'
                &&card[1].getValue()=='3' &&card[0].getValue()=='2';
    }

    private static boolean isFourOfaKind(Card card[]){
        return card[4].getValue()==card[1].getValue()||card[0].getValue()==card[3].getValue();
    }

    private static boolean isFullHouse(Card card[]){
        boolean highthree=card[0].getValue() == card[1].getValue() &&
                card[1].getValue() == card[2].getValue() &&
                card[3].getValue() == card[4].getValue();

        boolean hightwo=card[0].getValue() == card[1].getValue() &&
                card[2].getValue() == card[3].getValue() &&
                card[3].getValue() == card[4].getValue();
        return highthree||hightwo;
    }

    private static boolean isFlush(Card card[]){
        return isSameSuite(card);
    }

    private static boolean isStraight(Card card[]){
        return isContinousRankCards(card)||is5HighFlush(card);
    }

    private static boolean isThreeOfaKind(Card card[]){
        return card[0].getValue()==card[2].getValue()||
                card[1].getValue()==card[3].getValue()||
                card[2].getValue()==card[4].getValue();
    }

    private static boolean isTwoPair(Card card[]){
        Map<Character,Long> map=Arrays.stream(card).map(x->x.getValue()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return map.values().stream().filter(x->x==2).count()==2;
    }

    private static boolean isOnePair(Card card[]){
        Map<Character,Long> map=Arrays.stream(card).map(x->x.getValue()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return map.values().stream().filter(x->x==2).count()==1;
    }


}
