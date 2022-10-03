package com.WarRules;

import com.Player.Player;
import com.war.EndCases;

import java.util.ArrayList;
import java.util.Arrays;

public class WarTwo extends BaseRules{
    public static void warTwo (Player playerOne, Player playerTwo) {
        players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        viablePlayers.addAll(players);
        while(emptyDeckCheck()){
            beginRound();
            compareCards();
        }

        if(endCase == null){
            if(playerOne.getDeckSize() == 0 || playerOne.getDeckSize() == 0) {
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
            viablePlayers.get(0).lost();
        }
        else if(warCards.get(0).rank > warCards.get(1).rank){         //player 1 won
            viablePlayers.get(0).won(deckWon);
            viablePlayers.get(1).lost();
        }
        else {
            warPlayers.addAll(viablePlayers);
            war();
        }
    }
}
