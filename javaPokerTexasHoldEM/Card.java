package javaPokerTexasHoldEM;

/**
 *
 * @author mara
 */
import java.util.*;

public class Card {

    private short rank, suit;

    private static String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static char[] suits = {'h', 's', 'd', 'c'};

 //Constructor
    public Card(short rank, short suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Getters and Setters
    public short getSuit() {
        return suit;
    }

    public short getRank() {
        return rank;
    }

    protected void setSuit(short suit) {
        this.suit = suit;
    }

    protected void setRank(short rank) {
        this.rank = rank;
    }

    // methods
    public static String rankAsString(int rank) {
        return ranks[rank];
    }

    public static char suitAsString(int suit) {
        return suits[suit];
    }

    public @Override
    String toString() {
        return rank + " " + suit;
    }

    // to string
    protected String printCard() {
        return ranks[rank] + suits[suit];
    }

    // find if two cards are same in rank and suit
    public static boolean sameCard(Card card1, Card card2) {
        return (card1.rank == card2.rank && card1.suit == card2.suit);
    }

    @Override
    public boolean equals(Object that) {
        return this == that;
    }
}

class compareRank implements Comparator<Object> {

    public int compare(Object card1, Object card2) throws ClassCastException {

        if (!((card1 instanceof Card) && (card2 instanceof Card))) {
            throw new ClassCastException(card1.getClass() + "" + card2.getClass());
        }

        short rank1 = ((Card) card1).getRank();
        short rank2 = ((Card) card2).getRank();

        return rank1 - rank2;
    }
}

class compareSuit implements Comparator<Object> {

   

    public int compare(Object card1, Object card2) throws ClassCastException {
        
        if (!((card1 instanceof Card) && (card2 instanceof Card))) {
            throw new ClassCastException(card1.getClass() + "" + card2.getClass());
        }

        short suit1 = ((Card) card1).getSuit();
        short suit2 = ((Card) card2).getSuit();

        return suit1 - suit2;
    }
   
}
  