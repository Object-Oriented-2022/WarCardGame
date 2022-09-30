package com.war;

import java.util.ArrayList;
import java.util.Arrays;


public class WarVersions {

    public static void warOne (int maxRounds, Player playerOne, Player playerTwo) {
        int currentRound = 0;
        while(currentRound < maxRounds && playerOne.getDeckSize() > 0 && playerTwo.getDeckSize() > 0){
            Cards onesCard = playerOne.flipCards();
            Cards twosCard = playerTwo.flipCards();
            printCardsPulled(onesCard, twosCard);

            ArrayList<Cards> deckWon = new ArrayList<>(Arrays.asList(onesCard, twosCard));
            if(onesCard.rank < twosCard.rank){              //player 2 won
                System.out.println("Player 2 wins the round");
                playerTwo.won(deckWon);
            }
            else if(onesCard.rank > twosCard.rank){         //player 1 won
                System.out.println("Player 1 wins the round");
                playerOne.won(deckWon);
            }
            else {//TODO: WAR IF EQUAL
                System.out.println(onesCard.rank + " is equal to " + twosCard.rank);
                System.out.println("*** WAR! ***");
                // checks to make sure each player has enough cards to play war

                if (playerOne.getDeckSize() > 2 && playerTwo.getDeckSize() > 2) {
                    //players keep their cards if draw occurs, add to points deck to be won
                    System.out.println("Players have enough cards for war");

                    Cards onesCardFD = playerOne.flipCards();
                    Cards twosCardFD = playerTwo.flipCards();
                    Cards onesWarCard = playerOne.flipCards();
                    Cards twosWarCard = playerTwo.flipCards();

                    deckWon.addAll(Arrays.asList(onesCardFD, twosCardFD, onesWarCard, twosWarCard));

                    printCardsPulled(onesWarCard, twosWarCard);

                    if (onesWarCard.rank < twosWarCard.rank) {              //player 2 won
                        System.out.println("Player 2 wins the round");
                        //player two wins add all 6 cards to points pile
                        playerTwo.won(deckWon);
                    } else if (onesWarCard.rank > twosWarCard.rank) {         //player 1 won
                        System.out.println("Player 1 wins the round");
                        playerOne.won(deckWon);
                    } else { //equal again

                    }
                } else {
                    System.out.println("Draw, Player does not have enough cards to play WAR!!");
                }
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
    public static void warTwo (Player playerOne, Player playerTwo) {
        while(playerOne.getDeckSize() > 0 && playerTwo.getDeckSize() > 0){
            Cards onesCard = playerOne.flipCards();
            Cards twosCard = playerTwo.flipCards();
            printCardsPulled(onesCard, twosCard);

            ArrayList<Cards> deckWon = new ArrayList<>(Arrays.asList(onesCard, twosCard));
            if(onesCard.rank < twosCard.rank){              //player 2 won
                //System.out.println(onesCard.rank + " is less than " + twosCard.rank);
                System.out.println("Player 2 wins the round");
                //playerTwo.won(deckWon);
                playerTwo.addToPointsDeck(deckWon);
                //playerOne.removeCard(onesCard);
                //TODO: create helper function for printing??  --DONE
                printCurrentScore(playerOne.getPointsSize(), playerTwo.getPointsSize());

            }
            else if(onesCard.rank > twosCard.rank){         //player 1 won
                //System.out.println(onesCard.rank + " is greater than " + twosCard.rank);
                System.out.println("Player 1 wins the round");
                //playerOne.won(deckWon);
                playerOne.addToPointsDeck(deckWon);
                //playerTwo.removeCard(twosCard);
                //TODO: create helper function for printing??  --DONE
                printCurrentScore(playerOne.getPointsSize(), playerTwo.getPointsSize());
            }
            else {//TODO: WAR IF EQUAL
                //TODO: ASK ABOUT WAR VERSION 2????
                System.out.println(onesCard.rank + " is equal to " + twosCard.rank);
                System.out.println("*** WAR! ***");
                // checks to make sure each player has enough cards to play war
                if (playerOne.getDeckSize() > 2 && playerTwo.getDeckSize() > 2) {
                    //players keep their cards if draw occurs, add to points pile???
                    System.out.println("Players have enough cards for war");

                    Cards onesCardFD = playerOne.flipCards();
                    Cards twosCardFD = playerTwo.flipCards();
                    Cards onesWarCard = playerOne.flipCards();
                    Cards twosWarCard = playerTwo.flipCards();

                    deckWon.addAll(Arrays.asList(onesCardFD, twosCardFD, onesWarCard, twosWarCard));
                    /* OLD:
                    Cards onescardFD = playerOne.getTopCard();
                    Cards twoscardFD = playerTwo.getTopCard();
                    playerOne.removeCard(onescardFD);
                    playerTwo.removeCard(twoscardFD);
                    Cards oneswarCard = playerOne.getTopCard();
                    Cards twoswarCard = playerTwo.getTopCard();
                    playerOne.removeCard(oneswarCard);
                    playerTwo.removeCard(twoswarCard);
                     */
                    printCardsPulled(onesWarCard, twosWarCard);
                    //hello
                    //hi
                    //ArrayList<Cards> warDeckFD = new ArrayList<>(Arrays.asList(onesCardFD, twosCardFD));
                    //ArrayList<Cards> warDeckWon = new ArrayList<>(Arrays.asList(onesWarCard, twosWarCard));

                    if (onesWarCard.rank < twosWarCard.rank) {              //player 2 won
                        //System.out.println(onesCard.rank + " is less than " + twosCard.rank);
                        System.out.println("Player 2 wins the round");
                        //player two wins add all 6 cards to points pile
                        playerTwo.addToPointsDeck(deckWon);
                        //playerTwo.addToPointsDeck(warDeckFD);
                        //playerTwo.addToPointsDeck(warDeckWon);
                        //TODO: create helper function for printing?? --DONE
                        printCurrentScore(playerOne.getPointsSize(), playerTwo.getPointsSize());
                    } else if (onesWarCard.rank > twosWarCard.rank) {         //player 1 won
                        //System.out.println(onesCard.rank + " is greater than " + twosCard.rank);
                        System.out.println("Player 1 wins the round");
                        playerOne.addToPointsDeck(deckWon);
                        //playerOne.addToPointsDeck(warDeckFD);
                        //playerOne.addToPointsDeck(warDeckWon);
                        //TODO: create helper function for printing?? --DONE
                        printCurrentScore(playerOne.getPointsSize(), playerTwo.getPointsSize());
                    } else { //cards are equal again

                    }
                } else {
                    System.out.println("Draw, Player does not have enough cards to play WAR!!");
                }
            }
        }

        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());

    }

    private static void printCurrentScore(int playerOne, int playerTwo) {
        System.out.println("Player 1 has a score of " + playerOne);
        System.out.println("Player 2 has a score of " + playerTwo +
                "\n-----------------------------");
    }


}
