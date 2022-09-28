package com.war;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.war.DeckManipulation.halveDeck;


public class WarVersions {

    public static void warOne (int maxRounds, ArrayList<Cards> deck) {
        List<ArrayList<Cards>> playerDecks = halveDeck(deck);
        Player playerOne = new Player(playerDecks.get(0));
        Player playerTwo= new Player(playerDecks.get(1));

        int currentRound = 0;
        while(currentRound < maxRounds && playerOne.getDeckSize() > 0 && playerTwo.getDeckSize() > 0){
            Cards onesCard = playerOne.getTopCard();
            Cards twosCard = playerTwo.getTopCard();
            playerOne.removeCard(onesCard);
            playerTwo.removeCard(twosCard);

            printCardsPulled(onesCard, twosCard);


            ArrayList<Cards> deckWon = new ArrayList<>(Arrays.asList(onesCard, twosCard));
            if(onesCard.rank < twosCard.rank){              //player 2 won
                //System.out.println(onesCard.rank + " is less than " + twosCard.rank);
                System.out.println("Player 2 wins the round");
                playerTwo.won(deckWon);
                playerOne.removeCard(onesCard);
            }
            else if(onesCard.rank > twosCard.rank){         //player 1 won
                //System.out.println(onesCard.rank + " is greater than " + twosCard.rank);
                System.out.println("Player 1 wins the round");
                playerOne.won(deckWon);
                playerTwo.removeCard(twosCard);
            }
            else {//TODO: WAR IF EQUAL
                System.out.println(onesCard.rank + " is equal to " + twosCard.rank);
                System.out.println("*** WAR! ***");
                playerOne.won(deckWon);
                playerTwo.removeCard(twosCard);

            }
            currentRound++;
        }




        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());


    }

    private static void printCardsPulled(Cards onesCard, Cards twosCard) {
        System.out.println("Player 1" + onesCard.toString());
        System.out.println("Player 2" + twosCard.toString());

    }


}
