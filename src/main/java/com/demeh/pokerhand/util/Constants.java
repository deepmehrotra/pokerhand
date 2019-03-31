package com.demeh.pokerhand.util;

import java.util.HashMap;

public class Constants {

    public static final HashMap<Character,Integer> rankMap=new HashMap(){{
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
}
