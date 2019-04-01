package com.demeh.pokerhand.entities;

import com.demeh.pokerhand.util.Constants;
import com.demeh.pokerhand.util.HandProcessor;
import com.demeh.pokerhand.util.HandTypes;
import com.demeh.pokerhand.util.SortHelper;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * @Description This class is main immutable class for Poker Hand
 *              It contains card array which is final so it can not be modified further
 *              It also contains Card Type
 *              It exposes a static method which creates a new object every time called
 *              It implements comparable interface to every time Collection.sort is called , it will sort according to Hand Type
 */
public class PokerHand implements Comparable<PokerHand>{

    private final Card[] hand;

    private  String cardType;

    /**
     *
     * @param cardArray
     * @param cardType
     * @Descripiton Private constructor
     */
    private PokerHand(Card[] cardArray,String cardType){
        hand=cardArray;
        this.cardType=cardType;
    }

    /**
     *
     * @param cardsStringValue
     * @return
     * @Descripiton Static method to create new Hand. It takes cards value as string input
     */
    public static PokerHand fromString(String cardsStringValue){
        Assert.notNull(cardsStringValue,"Input string not a valid cards combination!!");
        Card[] cards=new Card[5];
        String cardsArray[]=cardsStringValue.split(" ");
        for(int i=0;i<cardsArray.length;i++){
            cards[i]=new Card(cardsArray[i]);
        }
        Arrays.sort(cards);
        return new PokerHand(cards,HandProcessor.getHandType(cards));
    }

    public Card[] getHand() {
        return hand;
    }

    public String getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        String result="<hand " + Arrays.toString(hand) +
                ",'" + cardType+"'>";
        if((cardType.equals(HandTypes.STRAIGHT_FLUSH.getHandType())||cardType.equals(HandTypes.STRAIGHT.getHandType()))&&hand[4].isAce()){
            Card temp[]=new Card[5];
            temp[0]=hand[4];
            temp[1]=hand[0];temp[2]=hand[1];temp[3]=hand[2];temp[4]=hand[3];
            result="<hand " + Arrays.toString(temp) +
                    ",'" + cardType+"'>";
        }
        return result;
    }


    @Override
    public int compareTo(PokerHand o) {
        if(!o.cardType.equals(this.cardType)){
            return Constants.rankOfHandsMap.get(o.cardType)<Constants.rankOfHandsMap.get(this.cardType)?1:-1;
        }
        return SortHelper.findHigherOfSameCardType(this,o);
    }


}
