package com.demeh.pokerhand.util;

import com.demeh.pokerhand.entities.PokerHand;

/**
 * This is utility class which contains sorting logic for HAnds if they are of same Type
 */
public class SortHelper {

    /**
     *
     * @param object
     * @param input
     * @return
     * @Description This method compare two hands of same card type . and sort them accordingly.
     */
    public static int findHigherOfSameCardType(PokerHand object,PokerHand input) {

        switch (input.getCardType())
        {
            case "ROYAL_FLUSH":
            case "FLUSH":
                return object.getHand()[4].getRank()>=input.getHand()[4].getRank()?1:-1;
            case "STRAIGHT_FLUSH":
            case "STRAIGHT":
                return compareInStraight(object, input);
            case "FOUR_OF_A_KIND":
                return compareInFourOfKind(object, input);
            case "FULL_HOUSE":
                return compareInFullHouse(object, input);
            case "THREE_OF_A_KIND":
                return compareInThreeOfKind(object, input);
            case "TWO_PAIR":
                return compareInTwoPair(object, input);
            case "ONE_PAIR":
                return compareInOnePair(object, input);
            default:
                return compareInHighCard(object, input);

        }

    }

    private static int compareInHighCard(PokerHand object, PokerHand input) {
        int temp=4;
        while(temp>=0){
            if(object.getHand()[4].getRank()==input.getHand()[4].getRank()){
                temp--;continue;
            }
            return object.getHand()[4].getRank()>input.getHand()[4].getRank()?1:-1;
        }
        return 1;
    }

    private static int compareInOnePair(PokerHand object, PokerHand input) {
        int thisPair=0;
        int inputPair=0;
        int inputRank=0,thisRank=0;
        int k=4;
        for(int i=0;i<4;i++){
            if(object.getHand()[i].getRank()==object.getHand()[i+1].getRank()){
                thisPair=i;
            }
            if(input.getHand()[i].getRank()==input.getHand()[i+1].getRank()){
                inputPair=i;
            }
        }
        thisRank=object.getHand()[thisPair].getRank();
        inputRank=input.getHand()[inputPair].getRank();
        if(thisRank==inputRank){
            while(k>=0){
                if(thisRank==object.getHand()[k].getRank()&&inputRank!=input.getHand()[k].getRank()){
                    return -1;
                }else if(thisRank!=object.getHand()[k].getRank()&&inputRank==input.getHand()[k].getRank()){
                    return 1;
                }else if(thisRank==object.getHand()[k].getRank()&&inputRank==input.getHand()[k].getRank()){
                    k--;continue;
                }else if(object.getHand()[k].getRank()>input.getHand()[k].getRank()){
                    return 1;
                }else if(object.getHand()[k].getRank()<input.getHand()[k].getRank()){
                    return -1;
                }else{
                    k--;
                }
            }
            return 1;
        }else{
            return thisRank>inputRank?1:-1;
        }
    }

    private static int compareInTwoPair(PokerHand object, PokerHand input) {
        int thisHihTwoPair=0;
        int thisSecTwoPair=0;
        int thisThirdCard=0;
        if(object.getHand()[4].getRank()==object.getHand()[3].getRank()){
            thisHihTwoPair=object.getHand()[4].getRank();
            if(object.getHand()[2].getRank()==object.getHand()[1].getRank()){
                thisSecTwoPair=object.getHand()[2].getRank();
                thisThirdCard=object.getHand()[0].getRank();

            }else{
                thisSecTwoPair=object.getHand()[1].getRank();
                thisThirdCard=object.getHand()[2].getRank();
            }
        }else{
            thisHihTwoPair=object.getHand()[3].getRank();
            thisSecTwoPair=object.getHand()[1].getRank();
            thisThirdCard=object.getHand()[4].getRank();
        }
                /*
                Checking paird of input object
                 */
        if(input.getHand()[4].getRank()==input.getHand()[3].getRank()){
            if(thisHihTwoPair==object.getHand()[4].getRank()){
                if(input.getHand()[2].getRank()==input.getHand()[1].getRank()){
                    if(thisSecTwoPair==input.getHand()[2].getRank()){
                        return (thisThirdCard>=input.getHand()[0].getRank())?1:-1;
                    }else{
                        return (thisSecTwoPair>input.getHand()[2].getRank())?1:-1;
                    }

                }else{
                    if(thisSecTwoPair==input.getHand()[1].getRank()){
                        return (thisThirdCard>=input.getHand()[2].getRank())?1:-1;
                    }else{
                        return (thisSecTwoPair>input.getHand()[1].getRank())?1:-1;
                    }
                }

            }else{
                return (thisHihTwoPair>input.getHand()[4].getRank())?1:-1;
            }


        }else{
            if(thisHihTwoPair==input.getHand()[3].getRank()){
                if(thisSecTwoPair==input.getHand()[1].getRank()){
                    return (thisThirdCard>=input.getHand()[4].getRank())?1:-1;
                }else{
                    return (thisSecTwoPair>input.getHand()[1].getRank())?1:-1;
                }
            }else{
                return (thisHihTwoPair>input.getHand()[3].getRank())?1:-1;
            }
        }
    }

    private static int compareInThreeOfKind(PokerHand object, PokerHand input) {
        int highThreeRank=0;
        int highSecRank=0;
        int highThirdRank=0;
        if(object.getHand()[4].getValue() == object.getHand()[2].getValue()){
            highThreeRank=object.getHand()[2].getRank();
            highSecRank=object.getHand()[1].getRank();
            highThirdRank=object.getHand()[0].getRank();
        }else if(object.getHand()[3].getValue() == object.getHand()[1].getValue()){
            highThreeRank=object.getHand()[3].getRank();
            highSecRank=object.getHand()[4].getRank();
            highThirdRank=object.getHand()[0].getRank();
        }else {
            highThreeRank=object.getHand()[2].getRank();
            highSecRank=object.getHand()[4].getRank();
            highThirdRank=object.getHand()[3].getRank();
        }

        if(input.getHand()[4].getValue() == input.getHand()[2].getValue()){
            if(highThreeRank==input.getHand()[2].getRank()){
                if(highSecRank==input.getHand()[1].getRank()){
                    return (highThirdRank>=input.getHand()[0].getRank())?1 :-1;
                }else{
                    return (highSecRank>input.getHand()[1].getRank())?1 :-1;
                }
            }else{
                return (highSecRank>input.getHand()[2].getRank())?1 :-1;
            }


        }else if(input.getHand()[3].getValue() == input.getHand()[1].getValue()){
            if(highThreeRank==input.getHand()[3].getRank()){
                if(highSecRank==input.getHand()[4].getRank()){
                    return (highThirdRank>=input.getHand()[0].getRank())?1 :-1;
                }else{
                    return (highSecRank>input.getHand()[4].getRank())?1 :-1;
                }
            }else{
                return (highThreeRank>input.getHand()[3].getRank())?1 :-1;
            }
        }else {
            if(highThreeRank==input.getHand()[0].getRank()){
                if(highSecRank==input.getHand()[4].getRank()){
                    return (highThirdRank>=input.getHand()[3].getRank())?1 :-1;
                }else{
                    return (highSecRank>input.getHand()[4].getRank())?1 :-1;
                }
            }else{
                return (highThreeRank>input.getHand()[0].getRank())?1 :-1;
            }
        }
    }

    private static int compareInFullHouse(PokerHand object, PokerHand input) {
        int thisHighRank;
        int inputHighRank;
        boolean thisHighThree=object.getHand()[0].getValue() == object.getHand()[1].getValue() &&
                object.getHand()[1].getValue() == object.getHand()[2].getValue() &&
                object.getHand()[3].getValue() == object.getHand()[4].getValue();
        boolean inputHighThree=input.getHand()[0].getValue() ==input.getHand()[1].getValue() &&
                input.getHand()[1].getValue() == input.getHand()[2].getValue() &&
                input.getHand()[3].getValue() == input.getHand()[4].getValue();
        thisHighRank=thisHighThree?object.getHand()[0].getRank():object.getHand()[4].getRank();
        inputHighRank=inputHighThree?input.getHand()[0].getRank():input.getHand()[4].getRank();
        return thisHighRank>=inputHighRank?1:-1;
    }

    private static int compareInFourOfKind(PokerHand object, PokerHand input) {
        int thisHighRank;
        int inputHighRank;
        thisHighRank=object.getHand()[4].getRank()==object.getHand()[1].getRank()?object.getHand()[4].getRank():object.getHand()[0].getRank();
        inputHighRank=input.getHand()[4].getRank()==input.getHand()[1].getRank()?input.getHand()[4].getRank():input.getHand()[0].getRank();
        return thisHighRank>=inputHighRank?1:-1;
    }

    private static int compareInStraight(PokerHand object, PokerHand input) {
        int thisHighRank;
        int inputHighRank;
        thisHighRank=(object.getHand()[4].isAce()&&object.getHand()[3].getRank()==4)?5:object.getHand()[4].getRank();
        inputHighRank=(input.getHand()[4].isAce()&&input.getHand()[3].getRank()==4)?5:input.getHand()[4].getRank();
        return thisHighRank>=inputHighRank?1:-1;
    }
}
