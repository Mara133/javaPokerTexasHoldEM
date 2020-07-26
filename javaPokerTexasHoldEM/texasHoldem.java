package javaPokerTexasHoldEM;

/**
 *
 * @author mara
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javaPokerTexasHoldEM.Player;

public class texasHoldem {

    public static void main(String[] args) throws Exception {

        Deck pokerDeck = new Deck();
        int numberOfPlayers = 0;
        int cardCounter = 0;
        int noNeededCounter = 0;
        int comunityCardCounter = 0;
        Board comunityCards = new Board();

        numberOfPlayers = getNumberOfPlayers();
        Player[] player = new Player[numberOfPlayers];

        //shuffle 3x
        for (int i = 0; i < 3; i++) {
            pokerDeck.shuffle();
        }

        // deck cut
        pokerDeck.cutDeck();

        // Initialize players
        for (int i = 0; i < numberOfPlayers; i++) {
            player[i] = new Player();
        }

        // Deal pocket cards to players
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                player[j].setCard(pokerDeck.getCard(cardCounter++), i);
            }
        }

        // dealing comunity cards
        comunityCards.setNoNeededCardsCard(pokerDeck.getCard(cardCounter++), noNeededCounter++);

        // deal flop
        for (int i = 0; i < 3; i++) {
            comunityCards.setBoardComunityCardsCard(pokerDeck.getCard(cardCounter++), comunityCardCounter++);
        }

        comunityCards.setNoNeededCardsCard(pokerDeck.getCard(cardCounter++), noNeededCounter++);

        // deal turn
        comunityCards.setBoardComunityCardsCard(pokerDeck.getCard(cardCounter++), comunityCardCounter++);

        comunityCards.setNoNeededCardsCard(pokerDeck.getCard(cardCounter++), noNeededCounter++);

        // deal river
        comunityCards.setBoardComunityCardsCard(pokerDeck.getCard(cardCounter++), comunityCardCounter++);

        //print comunity cards
        comunityCards.printBoard();

        // print player cards
       for (int i = 0; i < numberOfPlayers; i++) {

            player[i].printPlayerCards(i);

        }
        System.out.println("\n");
        //  hand comparison
   
        for (int i = 0; i < numberOfPlayers; i++) {
            handAlgorithm countingHand = new handAlgorithm();

            // fill board with player cards           
            for (int j = 0; j < player[i].pocketCardsSize(); j++) {
                countingHand.addCard(player[i].getCard(j), j);
            }

            //fill board with comunity cards
            for (int j = player[i].pocketCardsSize(); j < (player[i].pocketCardsSize() + comunityCards.boardSize()); j++) {
                countingHand.addCard(comunityCards.getBoardComunityCardsCard(j - player[i].pocketCardsSize()), j);
            }
            System.out.print("");
            System.out.println("<hand block" + (i + 1) + "> " + countingHand.algorithmHand());
        }
    }

    protected static int getNumberOfPlayers() throws Exception {
        int intPlayers = 0;
        String userInput = "";

        // Get number of players 
        System.out.println("Enter how many players will be (1-9):");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  System.out.println(" ");
        try {
            userInput = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error: cant read input!");
            System.exit(1);
        }

        // convert user input to an integer
        try {
            intPlayers = Integer.parseInt(userInput);
        } catch (NumberFormatException nfe) {
            System.out.println("Error: Input needs to be a number!");
            System.exit(1);
        }

        if ((intPlayers < 1) || (intPlayers > 9)) {
            throw new Exception("Error: Number of players neet to be between 1 and 9");
        }

        return intPlayers;
    }
}
