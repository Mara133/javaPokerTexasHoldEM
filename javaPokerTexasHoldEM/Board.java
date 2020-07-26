package javaPokerTexasHoldEM;

/**
 *
 * @author mara
 */
public class Board {

    private Card[] boardComunityCards = new Card[5];
    private Card[] noNeededCards = new Card[3];

    //constructor
    public Board() {
    }

    protected void setBoardComunityCardsCard(Card card, int cardNum) {
        this.boardComunityCards[cardNum] = card;
    }

    protected Card getBoardComunityCardsCard(int cardNum) {
        return this.boardComunityCards[cardNum];
    }

    protected void setNoNeededCardsCard(Card card, int cardNum) {
        this.noNeededCards[cardNum] = card;
    }

    protected Card getNoNeededCardsCard(int cardNum) {
        return this.noNeededCards[cardNum];
    }

    protected int boardSize() {
        return boardComunityCards.length;
    }

    protected void printBoard() {

        for (int i = 0; i < boardComunityCards.length; i++) {
            System.out.print(getBoardComunityCardsCard(i).printCard());
        }
        System.out.print(" ");
    }

    protected void printBurnCards() {
        System.out.println("The burn cards are:");
        for (int i = 0; i < noNeededCards.length; i++) {
            System.out.println(i + 1 + ": " + getNoNeededCardsCard(i).printCard());
        }
        System.out.println("\n");
    }

}
