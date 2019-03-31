package com.demeh.pokerhand.util;

public enum HandTypes {
    HIGH_CARD("HIGH_CARD"),
    ONE_PAIR("ONE_PAIR"),
    TWO_PAIR("TWO_PAIR"),
    THREE_OF_A_KIND("THREE_OF_A_KIND"),
    STRAIGHT("STRAIGHT"),
    FLUSH("FLUSH"),
    FULL_HOUSE("FULL_HOUSE"),
    FOUR_OF_A_KIND("FOUR_OF_A_KIND"),
    STRAIGHT_FLUSH("STRAIGHT_FLUSH"),
    ROYAL_FLUSH("ROYAL_FLUSH");

    private String handType;

    HandTypes(String handType) {
        this.handType=handType;
    }

    public String getHandType() {
        return this.handType;
    }

    public HandTypes getValue(String handType){
        return this.getValue(handType);
    }
}
