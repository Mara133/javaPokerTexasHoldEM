package javaPokerTexasHoldEM;

import java.util.Arrays;
import java.util.Comparator;


/**
 *
 * @author mara
 */
public class Player {

    private Card[] pocketCards = new Card[2];

    //constructor
    public Player() {
    }

    public Player(Card card1, Card card2) {
        pocketCards[0] = card1;
        pocketCards[1] = card2;
    }

    protected void setCard(Card card, int cardNum) {
        pocketCards[cardNum] = card;
    }

    protected Card getCard(int cardNum) {
        return pocketCards[cardNum];
    }

    protected int pocketCardsSize() {
        return pocketCards.length;
    }

    protected void printPlayerCards(int playerNumber) {

        for (int i = 0; i < 2; i++) {
        
            
            System.out.print(pocketCards[i].printCard());
        }
        System.out.print(" ");

    }
   
 
}
