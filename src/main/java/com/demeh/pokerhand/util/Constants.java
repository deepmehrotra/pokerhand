package com.demeh.pokerhand.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Constants {

    public static final HashMap<Character,Integer> rankMap=new HashMap<Character,Integer>(){{
        put('2',1);
        put('3',2);
        put('4',3);
        put('5',4);
        put('6',5);
        put('7',6);
        put('8',7);
        put('9',8);
        put('T',9);
        put('J',10);
        put('Q',11);
        put('K',12);
        put('A',13);

    }};

    public static final HashMap<String,Integer> rankOfHandsMap=new HashMap<String,Integer>(){{
        put("HIGH_CARD",1);
        put("ONE_PAIR",2);
        put("TWO_PAIR",3);
        put("THREE_OF_A_KIND",4);
        put("STRAIGHT",5);
        put("FLUSH",6);
        put("FULL_HOUSE",7);
        put("FOUR_OF_A_KIND",8);
        put("STRAIGHT_FLUSH",9);
        put("ROYAL_FLUSH",10);
    }};

    public static final Set<Character> suitsSet=new HashSet<Character>(){{
        add('S');add('D');add('H');add('C');
    }};
}
