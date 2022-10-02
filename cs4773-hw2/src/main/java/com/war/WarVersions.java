package com.war;

import com.Player.Player;
import com.card.Cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WarVersions {

    public static EndCases endCase = null;

    //TODO: MOVE CARDS INITIALIZATION HERE MAYBE
    public static void warOne (int maxRounds, Player playerOne, Player playerTwo) {
        int currentRound = 0;
        while(currentRound < maxRounds && playerOne.getDeckSize() > 0 && playerTwo.getDeckSize() > 0){
            Cards onesCard = playerOne.flipCards();
            Cards twosCard = playerTwo.flipCards();
            printCardsPulled(onesCard, twosCard);

            ArrayList<Cards> deckWon = new ArrayList<>(Arrays.asList(onesCard, twosCard));
            if(onesCard.rank < twosCard.rank){              //player 2 won
                playerTwo.won(deckWon);
            }
            else if(onesCard.rank > twosCard.rank){         //player 1 won
                playerOne.won(deckWon);
            }
            else {
                war(deckWon, playerOne, playerTwo);
            }
            if(endCase != null)
                break;
            currentRound++;
        }
        if(currentRound == maxRounds){
            endCase = EndCases.ROUNDS_END;
        }
        List<Integer> deckSizes = new ArrayList<>(Arrays.asList(playerOne.getPoints(), playerTwo.getPoints()));
        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());
        endGame(deckSizes);

    }

    //TODO: adjust for war v3
    private static void endGame(List<Integer> deckSizes) {
        int winner;
        switch(endCase){
            case ONE_WINNER: //one player has won all the cards
            case RAN_OUT:
                //winner = findWinner(deckSizes);
                findWinner(deckSizes);   //TODO DOES NOT WORK NEED TO MAKE DYNAMIC ON PLAYER AND ON # WINNERS
                //System.out.println("Player " + winner + " has won all of the cards");
                break;
            case ROUNDS_END:
                System.out.println("Max number of rounds played");
                findWinner(deckSizes); //TODO DOES NOT WORK NEED TO MAKE DYNAMIC ON PLAYER AND ON # WINNERS
                break;
            case DRAW_CARDS:
                System.out.println("Draw: Players last cards were equal cards");
                break;
            default:
                System.out.println("Some error occurred.");
        }
    }

    private static void findWinner(List<Integer> deckSizes) {
         /*int winner = 0, biggestDeck = 0;
        for (int i = 0; i < deckSizes.size(); i++) {
            int player = i+1;
            if (biggestDeck < deckSizes.get(i)) {
                biggestDeck = deckSizes.get(i);
                winner = player;
            }
            else if(biggestDeck == deckSizes.get(i)){
                winner.add(player);
            }
        }
       ArrayList<Integer> winners = new ArrayList<>();
        ArrayList<Integer> scores = new ArrayList<>();
        int biggestDeck = 0;
        for (int i = 0; i < deckSizes.size(); i++) {
            int player = i+1;
            if (biggestDeck < deckSizes.get(i)) {
                biggestDeck = deckSizes.get(i);
                winners.add(player);
            }
            else if(biggestDeck == deckSizes.get(i)){
                winners.add(player);
            }
        }
        if(winners.size() > 1){
            System.out.print("Tie: Players ");
            winners.forEach((n) -> { System.out.print(n + ", "); });
            System.out.println(" have deck sizes of ");
        }
        else{
            System.out.println("Player " + winners.get(0) + " has won the most cards");
        }
        return winner;*/
    }

    /*Most descriptions of War are not clear about what happens if a player runs out of cards
during a war. There are at least two possibilities:
If you don't have enough cards to complete the war, you lose. If neither player has enough cards,
the one who runs out first loses. If both run out simultaneously, it's a draw. Example: Players A
and B both play sevens, so there is a war. Each player plays a card face down, but this is player
B's last card. Player A wins, since player B does not have enough cards to fight the war.

If you run out of cards during a war, your last card is turned face up and is used for all battles
in that war. If this happens to both players in a war and their last cards are equal, the game is a
draw. Example: Players A and B both play sevens, so there is a war. Player A plays a card face
down, but player B has only one card, so it must be played face up. It is a queen. Player A plays
a card face up and it is also a queen, so the war must continue. Player B's queen stays (B's last
card) while player A plays a card face down and one face up, which is a nine. Player B wins the
war and takes all these seven cards (the five cards that A played and the two cards that B
played) and the game continues normally.*/
    //check decks before proceeding with iterations
    private static void war(ArrayList<Cards> deckWon, Player playerOne, Player playerTwo) {
        System.out.println("*** WAR! ***");
        if(checkDecks(playerOne) && checkDecks(playerTwo)){    //both are fine
            System.out.println("Players have enough cards for war");
            warIteration(deckWon, playerOne, playerTwo);
        }
        else if(!checkDecks(playerOne)){             //player one < 2
            System.out.println("Player 1 does not have enough cards to play war!");
            giveCards(deckWon, playerTwo, playerOne);
            endCase = EndCases.RAN_OUT;
        }
        else if(!checkDecks(playerTwo)){             //player two < 2
            System.out.println("Player 2 does not have enough cards to play war!");
            giveCards(deckWon, playerTwo, playerOne);
            endCase = EndCases.RAN_OUT;
        }
        else if(!checkDecks(playerOne) && !checkDecks(playerTwo)){      //draw scenario, both 1 card
            giveCardsBack(deckWon, playerOne, playerTwo);
            endCase = EndCases.DRAW_CARDS;
        }
    }

    private static void giveCards(ArrayList<Cards> deckWon, Player winner, Player loser) {
        deckWon.addAll(winner.deck);
        deckWon.addAll(loser.deck);
        winner.deck.clear();
        loser.deck.clear();
        winner.won(deckWon);
    }

    private static void giveCardsBack(ArrayList<Cards> deckWon, Player playerOne, Player playerTwo) {
        for(int i = 0; i < deckWon.size(); i++){
            playerOne.addCard(deckWon.get(i));
            playerTwo.addCard(deckWon.get(i+1));
            i++;    //increment past second card
        }
    }

    //check deck function
    private static boolean checkDecks(Player player) {
        return player.getDeckSize() > 2;
    }

    //begin war iterations
    private static void warIteration(ArrayList<Cards> deckWon, Player playerOne, Player playerTwo) {
        Cards onesCardFD = playerOne.flipCards();
        Cards twosCardFD = playerTwo.flipCards();
        Cards onesWarCard = playerOne.flipCards();
        Cards twosWarCard = playerTwo.flipCards();

        deckWon.addAll(Arrays.asList(onesCardFD, twosCardFD, onesWarCard, twosWarCard));

        printCardsPulled(onesWarCard, twosWarCard);

        if (onesWarCard.rank < twosWarCard.rank) {              //player 2 won
            //player two wins add all 6 cards to points pile
            playerTwo.won(deckWon);
            playerOne.lost();
        } else if (onesWarCard.rank > twosWarCard.rank) {         //player 1 won
            playerOne.won(deckWon);
            playerTwo.lost();
        } else { //equal again, start war over
            war(deckWon, playerOne, playerTwo);
        }
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
                playerTwo.won(deckWon);
                playerOne.lost();
            }
            else if(onesCard.rank > twosCard.rank){         //player 1 won
                playerOne.won(deckWon);
                playerTwo.lost();
            }
            else {
                System.out.println(onesCard.rank + " is equal to " + twosCard.rank);
                war(deckWon, playerOne, playerTwo);
            }
            if(endCase != null)
                break;
        }
        List<Integer> deckSizes = new ArrayList<>(Arrays.asList(playerOne.getPoints(), playerTwo.getPoints()));
        endGame(deckSizes);

        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());

    }

}
