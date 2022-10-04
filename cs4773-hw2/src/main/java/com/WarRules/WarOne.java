package com.WarRules;

import com.Player.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Subclass to BaseRules, utilizes Super's methods to play a game of war, version 1.
 */
public class WarOne extends BaseRules{
    /**
     * Starts war card game version one. This game has 2 players and recycles cards back into
     * the same deck. Returns nothing. Implements methods from
     * Rules Class.
     * @param maxRounds int number of round for a game
     * @param playerOne Player obj
     * @param playerTwo Player obj
     */
    public static void warOne (int maxRounds, Player playerOne, Player playerTwo) {
        int currentRound = 0;
        players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        viablePlayers.addAll(players);
        while(currentRound < maxRounds && emptyDeckCheck()){
            beginRound();
            compareCards();
            if(endCase != null)
                break;
            currentRound++;
        }
        if(endCase == null){
            if(currentRound == maxRounds){
                endCase = EndCases.ROUNDS_END;
            }
            else if(playerOne.getDeckSize() == 0 || playerOne.getDeckSize() == 0 ) {
                endCase = EndCases.ONE_WINNER;
            }
        }
        deckSizes.add(playerOne.getPoints());
        deckSizes.add(playerTwo.getPoints());
        findWinner();
        endGame();
    }
    private static void compareCards() {
        if(warCards.get(0).rank < warCards.get(1).rank){              //player 2 won
            viablePlayers.get(1).won(deckWon);
        }
        else if(warCards.get(0).rank > warCards.get(1).rank){         //player 1 won
            viablePlayers.get(0).won(deckWon);
        }
        else {
            warPlayers.addAll(viablePlayers);
            war();
        }
    }
}
