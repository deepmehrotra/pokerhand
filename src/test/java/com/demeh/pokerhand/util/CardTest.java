package com.demeh.pokerhand.util;

import com.demeh.pokerhand.entities.PokerHand;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class CardTest {

    @Test
    public void testCardSorting(){
        PokerHand pokerHand=PokerHand.fromString("4D 5D 3D 2D AD") ;
        Assert.assertEquals("<hand [AD, 2D, 3D, 4D, 5D],'STRAIGHT_FLUSH'>",pokerHand.toString());
    }

    @Test
    public void testCardSortingForRoyalFlush(){
        PokerHand pokerHand=PokerHand.fromString("TS JS QS KS AS");
        Assert.assertEquals("<hand [TS, JS, QS, KS, AS],'ROYAL_FLUSH'>",pokerHand.toString());
    }

    @Test
    public void testHandToString(){
        PokerHand pokerHand=PokerHand.fromString("4D 5D 3D 2D AD") ;
        System.out.println(pokerHand.toString());

    }

    @Test
    public void testSortInHandsList(){
        List<PokerHand> handList=new ArrayList<>();
        PokerHand royalFlush=PokerHand.fromString("TS JS QS KS AS") ;
        PokerHand straightFlush=PokerHand.fromString("5S 6S 7S 8S 9S") ;
        PokerHand fullHouse=PokerHand.fromString("5H 5C QD QC QS") ;
        PokerHand fourOfKind=PokerHand.fromString("7S TC TH TS TD") ;
        PokerHand flush=PokerHand.fromString("2D 3D 7D QD AD") ;
        PokerHand straight=PokerHand.fromString("4D 5D 6D 7H 8D") ;
        handList.add(royalFlush);handList.add(straightFlush);
        handList.add(fullHouse);handList.add(fourOfKind);
        handList.add(flush);handList.add(straight);
        Collections.sort(handList);
        Assert.assertEquals(HandTypes.STRAIGHT.getHandType(),handList.get(0).getCardType());
        Assert.assertEquals(HandTypes.ROYAL_FLUSH.getHandType(),handList.get(5).getCardType());

    }

    @Test
    public void testSortInStraightFlushWithAce(){
        List<PokerHand> handList=new ArrayList<>();
        PokerHand straightFlush=PokerHand.fromString("5S 6S 7S 8S 9S") ;
        PokerHand straightFlush1=PokerHand.fromString("4D 5D 3D 2D AD") ;
        PokerHand straightFlush2=PokerHand.fromString("7S 8S 9S TS JS") ;
        PokerHand straightFlush3=PokerHand.fromString("4S 5S 6S 7S 8S") ;
        PokerHand straightFlush4=PokerHand.fromString("8S 9S TS JS QS") ;
        handList.add(straightFlush1);handList.add(straightFlush);
        handList.add(straightFlush3);handList.add(straightFlush2);
        handList.add(straightFlush4);
        Collections.sort(handList);
        System.out.println(handList);
        Assert.assertEquals(13,handList.get(0).getHand()[4].getRank());

    }

    @Test
    public void testSortInStraightFlush(){
        List<PokerHand> handList=new ArrayList<>();
        PokerHand straightFlush=PokerHand.fromString("5S 6S 7S 8S 9S") ;
        PokerHand straightFlush1=PokerHand.fromString("6S 7S 8S 9S TS") ;
        PokerHand straightFlush2=PokerHand.fromString("7S 8S 9S TS JS") ;
        PokerHand straightFlush3=PokerHand.fromString("4S 5S 6S 7S 8S") ;
        PokerHand straightFlush4=PokerHand.fromString("8S 9S TS JS QS") ;
        handList.add(straightFlush1);handList.add(straightFlush);
        handList.add(straightFlush3);handList.add(straightFlush2);
        handList.add(straightFlush4);
        Collections.sort(handList);
        String result="[<hand [4S, 5S, 6S, 7S, 8S],'STRAIGHT_FLUSH'>, <hand [5S, 6S, 7S, 8S, 9S],'STRAIGHT_FLUSH'>, <hand [6S, 7S, 8S, 9S, TS],'STRAIGHT_FLUSH'>, <hand [7S, 8S, 9S, TS, JS],'STRAIGHT_FLUSH'>, <hand [8S, 9S, TS, JS, QS],'STRAIGHT_FLUSH'>]";
        Assert.assertEquals(result,handList.toString());

    }

    @Test
    public void testSortInThreeOFKind(){
        List<PokerHand> handList=new ArrayList<>();
        PokerHand tok=PokerHand.fromString("5S 5S 5D 8S 9S") ;
        PokerHand tok1=PokerHand.fromString("6S 6D 6S 9S TS") ;
        PokerHand tok2=PokerHand.fromString("3S 3S 3C TS JS") ;
        PokerHand tok3=PokerHand.fromString("3S 3S 3D 7S 8S") ;
        handList.add(tok);handList.add(tok1);
        handList.add(tok3);handList.add(tok2);
        Collections.sort(handList);
        String result="[<hand [3S, 3S, 3D, 7S, 8S],'THREE_OF_A_KIND'>, <hand [3S, 3S, 3C, TS, JS],'THREE_OF_A_KIND'>, <hand [5S, 5S, 5D, 8S, 9S],'THREE_OF_A_KIND'>, <hand [6S, 6D, 6S, 9S, TS],'THREE_OF_A_KIND'>]";
        Assert.assertEquals(result,handList.toString());

    }

    @Test
    public void testSortInTwoPair(){
        List<PokerHand> handList=new ArrayList<>();
        PokerHand topair=PokerHand.fromString("5S 5S 4D 4S 9S") ;
        PokerHand topair1=PokerHand.fromString("6S 6D 4S 4S TS") ;
        PokerHand topair2=PokerHand.fromString("5S 5S 4C 4S JS") ;
        PokerHand topair3=PokerHand.fromString("6S 6S 3D 3S 8S") ;
        handList.add(topair);handList.add(topair1);
        handList.add(topair3);handList.add(topair2);
        Collections.sort(handList);
        String result="[<hand [4D, 4S, 5S, 5S, 9S],'TWO_PAIR'>, <hand [4C, 4S, 5S, 5S, JS],'TWO_PAIR'>, <hand [3D, 3S, 6S, 6S, 8S],'TWO_PAIR'>, <hand [4S, 4S, 6S, 6D, TS],'TWO_PAIR'>]";
        Assert.assertEquals(result,handList.toString());

    }

    @Test
    public void testSortInOnePair(){
        List<PokerHand> handList=new ArrayList<>();
        PokerHand topair=PokerHand.fromString("5S 5S 3D 4S 9S") ;
        PokerHand topair1=PokerHand.fromString("6S 6D 2S 4S TS") ;
        PokerHand topair2=PokerHand.fromString("5S 5S AC 4S JS") ;
        PokerHand topair3=PokerHand.fromString("6S 6S AD 4S 8S") ;
        handList.add(topair);handList.add(topair1);
        handList.add(topair3);handList.add(topair2);
        Collections.sort(handList);
        String result="[<hand [3D, 4S, 5S, 5S, 9S],'ONE_PAIR'>, <hand [4S, 5S, 5S, JS, AC],'ONE_PAIR'>, <hand [2S, 4S, 6S, 6D, TS],'ONE_PAIR'>, <hand [4S, 6S, 6S, 8S, AD],'ONE_PAIR'>]";
        Assert.assertEquals(result,handList.toString());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullStringException(){
        PokerHand pokerHand=PokerHand.fromString(null) ;
    }
}
