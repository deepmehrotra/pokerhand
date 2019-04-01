package com.demeh.pokerhand.entities;

import com.demeh.pokerhand.util.Constants;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

/**
 * This is Card entity which hols ard value , suite and rank
 */
public class Card implements Comparable<Card>{

    private char suite ;
    private char value;
    private int rank;

    public Card(@NotNull String card){
        this.value=card.charAt(0);
        this.suite=card.charAt(1);
        validateInput();
        this.rank=Constants.rankMap.get(this.value);

    }

    private void validateInput() {
        if(!(Constants.rankMap.containsKey(this.value)&&Constants.suitsSet.contains(this.suite)))
            throw new IllegalArgumentException("Invalid values entered for card or suit");
    }

    public Card(@NotNull Character value,@NotNull Character suite){
        this.suite=suite;
        this.value=value;
        validateInput();
        this.rank=Constants.rankMap.get(this.value);
    }

    public char getSuite(){
        return this.suite;
    }

    public char getValue(){
        return this.value;
    }

    public boolean isAce(){
        return this.getRank()==13;
    }

    public boolean isA5(){
        return this.getValue()=='5';
    }

    public int getRank(){
        return this.rank;
    }


    @Override
    public int compareTo(Card o) {
       return this.getRank()>=o.getRank()?1:-1;

    }

    @Override
    public String toString() {
        return ""+value+suite;
    }
}
