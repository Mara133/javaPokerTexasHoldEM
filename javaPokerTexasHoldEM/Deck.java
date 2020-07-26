package javaPokerTexasHoldEM;

/**
 *
 * @author mara
 */
import java.util.Random;
// deck class what contains 52 cards in deck and shuffle them 

public class Deck {

    private Card[] cards = new Card[52];

    //Constructor
    public Deck() {
        int i = 0;
        for (short j = 0; j < 4; j++) {
            for (short k = 0; k < 13; k++) {
                cards[i++] = new Card(k, j);
            }
        }
    }

    // can print all deck out but im not calling method in main
    protected void printDeck() {
        for (int i = 0; i < cards.length; i++) {
            System.out.println(i + 1 + ": " + cards[i].printCard());
        }
        System.out.println("\n");
    }

    protected int findCard(Card card) {
        for (int i = 0; i < 52; i++) {
            if (Card.sameCard(cards[i], card)) {
                return i;
            }
        }
        return -1;
    }

    protected Card getCard(int cardNum) {
        return cards[cardNum];
    }

    protected void shuffle() {
        int length = cards.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < length; i++) {
            int change = i + random.nextInt(length - i);
            changeCards(i, change);
        }
    }

    protected void cutDeck() {
        Card[] temp = new Card[52];
        Random random = new Random();
        int cutNum = random.nextInt(52);

        System.arraycopy(this.cards, 0, temp, 52 - cutNum, cutNum);
        System.arraycopy(this.cards, cutNum, temp, 0, 52 - cutNum);

        this.cards = temp;
    }

    private void changeCards(int i, int change) {
        Card t = cards[i];
        cards[i] = cards[change];
        cards[change] = t;
    }
}
