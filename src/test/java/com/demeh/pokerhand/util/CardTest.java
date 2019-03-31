package com.demeh.pokerhand.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class CardTest {

    @Test
    public void testCardSorting(){
       // Card card1=new Card("4D 5D 6D JD TD AD");
       // PokerHand pokerHand=PokerHand.fromString("4D KD JD TD AD") ;
        PokerHand pokerHand=PokerHand.fromString("4D 5D 3D 2D AD") ;
       Arrays.stream(pokerHand.getHand()).forEach(x->System.out.println(x));

    }

    @Test
    public void testHandToString(){
        PokerHand pokerHand=PokerHand.fromString("4D 5D 3D 2D AD") ;
        System.out.println(pokerHand.toString());

    }
}
