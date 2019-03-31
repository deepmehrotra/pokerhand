package com.demeh.pokerhand.util;

import javax.validation.constraints.NotNull;

public class Card implements Comparable<Card>{

    private char suite ;
    private char value;
    private int rank;

    public Card(@NotNull String card){
        this.value=card.charAt(0);
        this.suite=card.charAt(1);
        this.rank=Constants.rankMap.get(this.value);

    }

    public Card(@NotNull Character value,@NotNull Character suite){
        this.suite=suite;
        this.value=value;
        this.rank=Constants.rankMap.get(this.value);
    }

    public char getSuite(){
        return this.suite;
    }

    public char getValue(){
        return this.value;
    }

    public boolean isAce(){
        return this.getValue()=='A';
    }

    public boolean isA5(){
        return this.getValue()=='5';
    }

    public int getRank(){
        return this.rank;
    }


    @Override
    public int compareTo(Card o) {
        if(this.getValue()=='A') return 1;
        if (this.getValue()=='T' && (o.getValue()=='A'||o.getValue()=='J'||o.getValue()=='Q'||o.getValue()=='K')) return -1;
        if(this.getValue()==o.getValue()) return 0;
        if(this.getValue()<o.getValue()) return -1;
        else return 1;

    }

    @Override
    public String toString() {
        return ""+value+suite;
    }
}
