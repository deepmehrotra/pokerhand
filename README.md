# pokerhand

Spring boot app to handle poker hand creation and sorting
--Testing change propagation
 
 
  @Override
    public int compareTo(PokerHand o) {
        if(!o.cardType.equals(this.cardType)){
            return Constants.rankOfHandsMap.get(o.cardType)<Constants.rankOfHandsMap.get(this.cardType)?1:-1;
        }
        return findHigherOfSameCardType(o);
    }

    private int findHigherOfSameCardType(PokerHand o) {

        int thisHighRank;
        int inputHighRank;
        switch (o.getCardType())
        {
            case "ROYAL_FLUSH":
                return this.hand[4].getRank()>=o.hand[4].getRank()?1:-1;
            case "STRAIGHT_FLUSH":
            case "STRAIGHT":
                thisHighRank=(this.hand[4].isAce()&&this.hand[3].getRank()==4)?5:this.hand[4].getRank();
                inputHighRank=(o.hand[4].isAce()&&o.hand[3].getRank()==4)?5:o.hand[4].getRank();
                return thisHighRank>=inputHighRank?1:-1;
            case "FOUR_OF_A_KIND":
                thisHighRank=this.hand[4].getRank()==this.hand[1].getRank()?this.hand[4].getRank():this.hand[0].getRank();
                inputHighRank=o.hand[4].getRank()==o.hand[1].getRank()?o.hand[4].getRank():o.hand[0].getRank();
                return thisHighRank>=inputHighRank?1:-1;
            case "FULL_HOUSE":
                boolean thisHighThree=this.hand[0].getValue() == this.hand[1].getValue() &&
                        this.hand[1].getValue() == this.hand[2].getValue() &&
                        this.hand[3].getValue() == this.hand[4].getValue();
                boolean inputHighThree=o.hand[0].getValue() ==o.hand[1].getValue() &&
                        o.hand[1].getValue() == o.hand[2].getValue() &&
                        o.hand[3].getValue() == o.hand[4].getValue();
                thisHighRank=thisHighThree?this.hand[0].getRank():this.hand[4].getRank();
                inputHighRank=inputHighThree?o.hand[0].getRank():o.hand[4].getRank();
                return thisHighRank>=inputHighRank?1:-1;
            case "FLUSH":
                return this.hand[4].getRank()>=o.hand[4].getRank()?1:-1;
            case "THREE_OF_A_KIND":
                thisHighRank=Arrays.stream(this.hand).map(x->x.getRank()).collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting())).entrySet().stream().filter(x->x.getValue()==3).findFirst().get().getKey();



        }

    }
