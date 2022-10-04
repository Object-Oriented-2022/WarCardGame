package com.WarRules;

import com.Player.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds all the basic functionalities used to implement war games
 */
public abstract class BaseRules extends WarBehavior {
    private static final ArrayList<Player> winners = new ArrayList<>();
    static boolean emptyDeckCheck(){
        for(Player player : players){
            if(player.getDeckSize() == 0) {
                viablePlayers.remove(player);
            }
        }
        if(viablePlayers.size() <= 1){
            endCase = EndCases.ONE_WINNER;
            return false;
        }
        return true;
    }
    static void beginRound(){
        resetRound();
        warCards = pullCards(viablePlayers);
        printCardsPulled(warCards);
        deckWon.addAll(warCards);
    }

    static void resetRound(){
        winner = null;
        deckWon.clear();
        warCards.clear();
        warPlayers.clear();
    }





    static void findWinner() {
        int biggestDeck = Collections.max(deckSizes);
        int indexOfDeck = deckSizes.indexOf(biggestDeck);
        deckSizes.remove(indexOfDeck);
        winner = players.get(indexOfDeck);
        winners.add(winner);
        if(deckSizes.contains(biggestDeck))
            findWinner();
    }

    static void endGame() {
        switch(endCase){
            case ONE_WINNER:
            case RAN_OUT:
                printWinner();
                break;
            case ROUNDS_END:
                System.out.println("Max number of rounds played");
                printWinner();
                break;
            case DRAW_CARDS:
                System.out.println("Draw: Players last cards were equal cards");
                printWinner();
                break;
            default:
                System.out.println("Some error occurred.");
                break;
        }
    }

    private static void printWinner() {
        if(winners.size() == 1) {
            System.out.print("Player " + winners.get(0).playerNum + " has won the game with ");
            System.out.println(winners.get(0).getPoints() + " cards");
        } else {
            for (Player player : winners) {
                System.out.print("Player " + player.playerNum + " has won the game with ");
                System.out.print(player.getPoints());
            }
        }
    }
}