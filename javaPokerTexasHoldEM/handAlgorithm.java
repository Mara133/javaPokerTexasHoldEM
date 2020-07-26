package javaPokerTexasHoldEM;

/**
 *
 * @author mara
 */
import java.util.Arrays;

public class handAlgorithm {

    private Card[] accessibleCards = new Card[7];

    private final static short ONE = 1;
    private final static short TWO = 2;
    private final static short THREE = 3;
    private final static short FOUR = 4;
    private int[] pocketCards;

    // Constructor
    public handAlgorithm() {
    }

    protected void addCard(Card card, int i) {
        accessibleCards[i] = card;
    }

    protected Card getCard(int i) {
        return accessibleCards[i];
    }

    protected int numCards() {
        return accessibleCards.length;
    }

    protected void sortRank() {
        Arrays.sort(accessibleCards, new compareRank());
    }

    protected void sortSuit() {
        Arrays.sort(accessibleCards, new compareSuit());
    }

    protected void sortSuitAndRank() {
        Arrays.sort(accessibleCards, new compareSuit());
        Arrays.sort(accessibleCards, new compareRank());
    }

    protected void sortRankAndsSuit() {
        Arrays.sort(accessibleCards, new compareRank());
        Arrays.sort(accessibleCards, new compareSuit());
    }

    protected String algorithmHand() {
        String handResult = new String();
        short[] rankCounter = new short[13];
        short[] suitCounter = new short[4];

        // initializations
        for (int i = 0; i < rankCounter.length; i++) {
            rankCounter[i] = 0;
        }

        for (int i = 4; i < suitCounter.length; i++) {
            suitCounter[i] = 0;
        }

        // sort cards and ranks
        for (int i = 0; i < accessibleCards.length; i++) {
            rankCounter[accessibleCards[i].getRank()]++;
            suitCounter[accessibleCards[i].getSuit()]++;
        }

        //sort cards for hand strenght algorithm
        this.sortRankAndsSuit();

        // checking for royal flush
        handResult = royalAlgorithm(rankCounter, suitCounter);

        // checking for straight flush
        if (handResult == null || handResult.length() == 0) {
            handResult = straightFlushAlgorithm(rankCounter, suitCounter);
        }

        // checking for four of a kind
        if (handResult == null || handResult.length() == 0) {
            handResult = fourOfAKindAlgorithm(rankCounter);
        }

        // checking for full house
        if (handResult == null || handResult.length() == 0) {
            handResult = fullHouseAlgorithm(rankCounter);
        }

        // checking for flush
        if (handResult == null || handResult.length() == 0) {
            handResult = flushAlgorithm(rankCounter, suitCounter);
        }

        // checking for straight
        if (handResult == null || handResult.length() == 0) {

            this.sortRank();
            handResult = straightAlgorithm(rankCounter);
        }

        // checking for three of a kind
        if (handResult == null || handResult.length() == 0) {
            handResult = threeOfAKindAlgorithm(rankCounter);
        }

        // checking for two pair
        if (handResult == null || handResult.length() == 0) {
            handResult = twoPairAlgorithm(rankCounter);
        }

        // checking for one pair
        if (handResult == null || handResult.length() == 0) {
            handResult = onePairAlgorithm(rankCounter);
        }

        // checking for highCard
        if (handResult == null || handResult.length() == 0) {
            handResult = highCardAlgorithm(rankCounter);
        }

        return handResult;
    }

    private String royalAlgorithm(short[] rankCounter, short[] suitCounter) {
        String result = "";

        // checking for Royal Flush 
        if ((rankCounter[9] >= 1
                && rankCounter[10] >= 1
                && rankCounter[11] >= 1
                && rankCounter[12] >= 1
                && rankCounter[0] >= 1)
                && (suitCounter[0] > 4 || suitCounter[1] > 4
                || suitCounter[2] > 4 || suitCounter[3] > 4)) {

            royalAlgorithm:
            for (int i = 0; i < 3; i++) {

                if (accessibleCards[i].getRank() == 0) {

                    for (int j = 1; j < 4 - i; j++) {
                        if ((accessibleCards[i + j].getRank() == 9
                                && accessibleCards[i + j + 1].getRank() == 10
                                && accessibleCards[i + j + 2].getRank() == 11
                                && accessibleCards[i + j + 3].getRank() == 12)
                                && (accessibleCards[i].getSuit() == accessibleCards[i + j].getSuit()
                                && accessibleCards[i].getSuit() == accessibleCards[i + j + 1].getSuit()
                                && accessibleCards[i].getSuit() == accessibleCards[i + j + 2].getSuit()
                                && accessibleCards[i].getSuit() == accessibleCards[i + j + 3].getSuit())) {

                            result = "Royal Flush is hand block with most strongest value!" + Card.suitAsString(accessibleCards[i].getSuit());
                            break royalAlgorithm;
                        }
                    }
                }
            }
        }
        return result;
    }

    // Straight flush 
    private String straightFlushAlgorithm(short[] rankCounter, short[] suitCounter) {
        String result = "";

        if (suitCounter[0] > 4 || suitCounter[1] > 4
                || suitCounter[2] > 4 || suitCounter[3] > 4) {

            for (int i = accessibleCards.length - 1; i > 3; i--) {
                if ((accessibleCards[i].getRank() - ONE == accessibleCards[i - ONE].getRank()
                        && accessibleCards[i].getRank() - TWO == accessibleCards[i - TWO].getRank()
                        && accessibleCards[i].getRank() - THREE == accessibleCards[i - THREE].getRank()
                        && accessibleCards[i].getRank() - FOUR == accessibleCards[i - FOUR].getRank())
                        && (accessibleCards[i].getSuit() == accessibleCards[i - ONE].getSuit()
                        && accessibleCards[i].getSuit() == accessibleCards[i - TWO].getSuit()
                        && accessibleCards[i].getSuit() == accessibleCards[i - THREE].getSuit()
                        && accessibleCards[i].getSuit() == accessibleCards[i - FOUR].getSuit())) {

                    result = "Straight Flush is hand block with second strongest value!" + Card.rankAsString(accessibleCards[i].getRank()) + Card.suitAsString(accessibleCards[i].getSuit());
                    break;
                }
            }
        }
        return result;
    }

    // Four of a kind 
    private String fourOfAKindAlgorithm(short[] rankCounter) {
        String result = "";

        for (int i = 0; i < rankCounter.length; i++) {
            if (rankCounter[i] == FOUR) {
                result = "Four of a Kind is hand block with 3th strongest value! " + Card.rankAsString(i);
                break;
            }
        }
        return result;
    }

    // Full house 
    private String fullHouseAlgorithm(short[] rankCounter) {
        String result = "";
        short threeOfKindRank = -1;
        short twoOfKindRank = -1;

        for (int i = rankCounter.length; i > 0; i--) {
            if ((threeOfKindRank < (short) 0) || (twoOfKindRank < (short) 0)) {
                if ((rankCounter[i - ONE]) > 2) {
                    threeOfKindRank = (short) (i - ONE);
                } else if ((rankCounter[i - ONE]) > 1) {
                    twoOfKindRank = (short) (i - ONE);
                }
            } else {
                break;
            }
        }

        if ((threeOfKindRank >= (short) 0) && (twoOfKindRank >= (short) 0)) {
            result = "Full House is hand block with 4th strongest value! " + Card.rankAsString(threeOfKindRank) + "'s full of " + Card.rankAsString(twoOfKindRank);
        }

        return result;
    }

    // Flush 
    private String flushAlgorithm(short[] rankCounter, short[] suitCounter) {
        String result = "";

        if (suitCounter[0] > 4 || suitCounter[1] > 4
                || suitCounter[2] > 4 || suitCounter[3] > 4) {

            for (int i = accessibleCards.length - 1; i > 3; i--) {
                if (accessibleCards[i].getSuit() == accessibleCards[i - ONE].getSuit()
                        && accessibleCards[i].getSuit() == accessibleCards[i - TWO].getSuit()
                        && accessibleCards[i].getSuit() == accessibleCards[i - THREE].getSuit()
                        && accessibleCards[i].getSuit() == accessibleCards[i - FOUR].getSuit()) {

                    result = "Flush is hand block with 5th strongest value! " + Card.rankAsString(accessibleCards[i].getRank()) + " high " + Card.suitAsString(accessibleCards[i].getSuit());
                    break;
                }
            }
        }

        return result;
    }

    // Straight 
    private String straightAlgorithm(short[] rankCounter) {
        String result = "";

        for (int i = rankCounter.length; i > 4; i--) {
            if ((rankCounter[i - 1] > 0)
                    && (rankCounter[i - 2] > 0)
                    && (rankCounter[i - 3] > 0)
                    && (rankCounter[i - 4] > 0)
                    && (rankCounter[i - 5] > 0)) {
                result = "Straight is hand block with 6th strongest value!" + Card.rankAsString(i - 1) + " high";
                break;
            }
        }
        return result;
    }

    // Three of a kind 
    private String threeOfAKindAlgorithm(short[] rankCounter) {
        String result = "";

        for (int i = rankCounter.length; i > 0; i--) {
            if (rankCounter[i - 1] > 2) {
                result = "Three of a Kind is 7th strongest value of hand block" + Card.rankAsString(i - 1) + "'s";
                break;
            }
        }
        return result;
    }

    // Two pair
    private String twoPairAlgorithm(short[] rankCounter) {
        String result = "";
        short firstPairRank = -1;
        short secondPairRank = -1;

        for (int i = rankCounter.length; i > 0; i--) {
            if ((firstPairRank < (short) 0) || (secondPairRank < (short) 0)) {
                if (((rankCounter[i - ONE]) > 1) && (firstPairRank < (short) 0)) {
                    firstPairRank = (short) (i - ONE);
                } else if ((rankCounter[i - ONE]) > 1) {
                    secondPairRank = (short) (i - ONE);
                }
            } else {
                break;
            }
        }

        if ((firstPairRank >= (short) 0) && (secondPairRank >= (short) 0)) {
            if (secondPairRank == (short) 0) {

                result = "Two Pair is hand block with 3th weakest value! " + Card.rankAsString(secondPairRank) + "'s and " + Card.rankAsString(firstPairRank) + "'s";
            } else {
                result = "Two Pair is hand block with 3th weakest value! " + Card.rankAsString(firstPairRank) + "'s and " + Card.rankAsString(secondPairRank) + "'s";
            }
        }

        return result;
    }

    // One pair
    private String onePairAlgorithm(short[] rankCounter) {
        String result = "";

        for (int i = rankCounter.length; i > 0; i--) {
            if ((rankCounter[i - ONE]) > 1) {
                result = "One Pair is hand block with second weakest value! " + Card.rankAsString(i - ONE) + "'s";
                break;
            }
        }
        return result;
    }

    // high card 
    private String highCardAlgorithm(short[] rankCounter) {
        String result = "";

        for (int i = rankCounter.length; i > 0; i--) {
            if ((rankCounter[i - ONE]) > 0) {
                result = "High Card is hand block with most weakest value " + Card.rankAsString(i - ONE);
                break;
            }
        }
        return result;
    }

    //find if equal sort by alphabet
    private int compareTo(handAlgorithm that) {
        for (int i = 0; i < 6; i++) {
            Arrays.sort(pocketCards);
            if (this.pocketCards[i] == that.pocketCards[i]) {

                System.out.print(pocketCards + "=" + pocketCards);
            }
            if (this.pocketCards[i] > that.pocketCards[i]) {
                return 1;
            } else if (this.pocketCards[i] < that.pocketCards[i]) {
                return -1;
            }
        }
        return 0; //if hands are equal
    }
}
