package com.demeh.pokerhand.util;

import java.util.Arrays;

public class PokerHand {

    private Card[] hand;

    private  String cardType;

    private PokerHand(Card[] cardArray,String cardType){
        hand=cardArray;
        this.cardType=cardType;
    }

    public static PokerHand fromString(String cardsStringValue){
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
        if((cardType.equals(HandTypes.STRAIGHT_FLUSH)||cardType.equals(HandTypes.STRAIGHT))&&hand[4].isAce()){
            Card temp[]=hand;
            temp[0]=hand[4];
            temp[1]=hand[0];temp[2]=hand[1];temp[3]=hand[2];temp[4]=hand[3];
            result="<hand " + Arrays.toString(temp) +
                    ",'" + cardType+"'>";
        }
        return result;
    }
}
