package com.demeh.pokerhand.util;

import com.demeh.pokerhand.entities.Card;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class to process Hands Type
 */
public class HandProcessor {
    /**
     *
     * @param card
     * @return String
     * @Description This method takes card array as input and returns Hand Type
     */
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

    /**
     *
     * @param card
     * @return boolean
     * @Description Method checks wheather the card array has same suite for all cards or not
     */
    private static boolean isSameSuite(Card card[]){
       return Arrays.stream(card).map(x->new Character(x.getSuite())).distinct().count()==1;
    }

    /**
     *
     * @param card
     * @return boolean
     * @Description This method checks if the cards arays contains continuos cards like 5,6,7,8,9
     * It considers A as highest card
     */
    private static boolean isContinousRankCards(Card card[]){
       for(int i=0;i<card.length-1;i++){
           int diff=Constants.rankMap.get(card[i+1].getValue())-Constants.rankMap.get(card[i].getValue());
           if(diff!=1){
               return false;
           }
       }
       return true;
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is Royal Flush
     */
    private static boolean isRoyalFlush(Card card[]){
        return isSameSuite(card)&&isContinousRankCards(card)&&card[4].isAce();
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is Straight Flush
     */
    private static boolean isStraightFlush(Card card[]){
        return isSameSuite(card)&&(is5HighFlush(card)||isContinousRankCards(card));
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is straight with Ace like A ,2 ,3,4,5 where Ace is considered as lowest card
     */
    private static boolean is5HighFlush(Card card[]){
        return card[4].isAce()&&card[3].getValue()=='5'&&card[2].getValue()=='4'
                &&card[1].getValue()=='3' &&card[0].getValue()=='2';
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is Four of same kind
     */
    private static boolean isFourOfaKind(Card card[]){
        return card[4].getValue()==card[1].getValue()||card[0].getValue()==card[3].getValue();
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is Full house
     */
    private static boolean isFullHouse(Card card[]){
        boolean highthree=card[0].getValue() == card[1].getValue() &&
                card[1].getValue() == card[2].getValue() &&
                card[3].getValue() == card[4].getValue();

        boolean hightwo=card[0].getValue() == card[1].getValue() &&
                card[2].getValue() == card[3].getValue() &&
                card[3].getValue() == card[4].getValue();
        return highthree||hightwo;
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is Flush
     */
    private static boolean isFlush(Card card[]){
        return isSameSuite(card);
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is Straight
     */
    private static boolean isStraight(Card card[]){
        return isContinousRankCards(card)||is5HighFlush(card);
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is Three of a kind
     */
    private static boolean isThreeOfaKind(Card card[]){
        return card[0].getValue()==card[2].getValue()||
                card[1].getValue()==card[3].getValue()||
                card[2].getValue()==card[4].getValue();
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is Two pair
     */
    private static boolean isTwoPair(Card card[]){
        Map<Character,Long> map=Arrays.stream(card).map(x->x.getValue()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return map.values().stream().filter(x->x==2).count()==2;
    }

    /**
     *
     * @param card
     * @return
     * @Description This method check if the cards array is Single Pair
     */
    private static boolean isOnePair(Card card[]){
        Map<Character,Long> map=Arrays.stream(card).map(x->x.getValue()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return map.values().stream().filter(x->x==2).count()==1;
    }


}
