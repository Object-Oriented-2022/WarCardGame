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

    // A different version for two players. Cards that are won are placed in a separate points pile.
    // Cards are not recycled. The game ends after the players use the cards that were initially dealt to them.
    // The winner is the player with the most cards in his/her points pile at the end of the game.
    // If the players do not have enough cards to complete a war, that round is a draw, and they keep their cards.
    public static void warTwo (ArrayList<Cards> deck) {
        List<ArrayList<Cards>> playerDecks = halveDeck(deck);
        Player playerOne = new Player(playerDecks.get(0));
        Player playerTwo= new Player(playerDecks.get(1));

        while(playerOne.getDeckSize() > 0 && playerTwo.getDeckSize() > 0){
            Cards onesCard = playerOne.getTopCard();
            Cards twosCard = playerTwo.getTopCard();
            playerOne.removeCard(onesCard);
            playerTwo.removeCard(twosCard);

            printCardsPulled(onesCard, twosCard);

            ArrayList<Cards> deckWon = new ArrayList<>(Arrays.asList(onesCard, twosCard));

            if(onesCard.rank < twosCard.rank){              //player 2 won
                //System.out.println(onesCard.rank + " is less than " + twosCard.rank);
                System.out.println("Player 2 wins the round");
                //playerTwo.won(deckWon);
                playerTwo.addToPointsDeck(deckWon);
                playerOne.removeCard(onesCard);
                // create helper function for printing??
                System.out.println("Player 1 has a score of " + playerOne.getPointsSize());
                System.out.println("Player 2 has a score of " + playerTwo.getPointsSize()+
                        "\n-----------------------------");
            }
            else if(onesCard.rank > twosCard.rank){         //player 1 won
                //System.out.println(onesCard.rank + " is greater than " + twosCard.rank);
                System.out.println("Player 1 wins the round");
                //playerOne.won(deckWon);
                playerOne.addToPointsDeck(deckWon);
                playerTwo.removeCard(twosCard);
                System.out.println("Player 1 has a score of " + playerOne.getPointsSize());
                System.out.println("Player 2 has a score of " + playerTwo.getPointsSize()+
                        "\n-----------------------------");
            }
            else {//TODO: WAR IF EQUAL
                //TODO: ASK ABOUT WAR VERSION 2????
                System.out.println(onesCard.rank + " is equal to " + twosCard.rank);
                System.out.println("*** WAR! ***");
                // checks to make sure each player has enough cards to play war
                if (playerOne.getDeckSize() > 2 && playerTwo.getDeckSize() > 2) {
                    //players keep their cards if draw occurs, add to points pile???
                    System.out.println("Players have enough cards for war");

                    Cards onescardFD = playerOne.getTopCard();
                    Cards twoscardFD = playerTwo.getTopCard();
                    playerOne.removeCard(onescardFD);
                    playerTwo.removeCard(twoscardFD);
                    Cards oneswarCard = playerOne.getTopCard();
                    Cards twoswarCard = playerTwo.getTopCard();
                    playerOne.removeCard(oneswarCard);
                    playerTwo.removeCard(twoswarCard);

                    printCardsPulled(oneswarCard, twoswarCard);

                    ArrayList<Cards> wardeckFD = new ArrayList<>(Arrays.asList(onescardFD, twoscardFD));
                    ArrayList<Cards> wardeckWon = new ArrayList<>(Arrays.asList(oneswarCard, twoswarCard));

                    if (oneswarCard.rank < twoswarCard.rank) {              //player 2 won
                        //System.out.println(onesCard.rank + " is less than " + twosCard.rank);
                        System.out.println("Player 2 wins the round");
                        //player two wins add all 6 cards to points pile
                        playerTwo.addToPointsDeck(deckWon);
                        playerOne.removeCard(onesCard);
                        playerTwo.addToPointsDeck(wardeckFD);
                        playerTwo.addToPointsDeck(wardeckWon);
                        playerOne.removeCard(onescardFD);
                        playerOne.removeCard(oneswarCard);
                        // create helper function for printing??
                        System.out.println("Player 1 has a score of " + playerOne.getPointsSize());
                        System.out.println("Player 2 has a score of " + playerTwo.getPointsSize()+
                                "\n-----------------------------");
                    } else if (oneswarCard.rank > twoswarCard.rank) {         //player 1 won
                        //System.out.println(onesCard.rank + " is greater than " + twosCard.rank);
                        System.out.println("Player 1 wins the round");
                        playerOne.addToPointsDeck(deckWon);
                        playerTwo.removeCard(twosCard);
                        playerOne.addToPointsDeck(wardeckFD);
                        playerOne.addToPointsDeck(wardeckWon);
                        playerTwo.removeCard(twoscardFD);
                        playerTwo.removeCard(twoswarCard);
                        System.out.println("Player 1 has a score of " + playerOne.getPointsSize());
                        System.out.println("Player 2 has a score of " + playerTwo.getPointsSize() +
                                "\n-----------------------------");
                    }
                } else {
                    System.out.println("Draw, Player does not have enough cards to play WAR!!");
                }
            }
        }

        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());

    }


}
