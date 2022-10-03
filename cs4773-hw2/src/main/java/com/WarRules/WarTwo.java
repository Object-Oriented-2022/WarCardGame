package com.WarRules;

import com.Player.Player;
import com.war.EndCases;

import java.util.ArrayList;
import java.util.Arrays;

public class WarTwo extends BaseRules{
    public static void WarTwo (Player playerOne, Player playerTwo) {
        players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        while(!emptyDeckCheck()){
            resetRound();
            warCards = pullCards(players);
            printCardsPulled(warCards);
            deckWon.addAll(warCards);

            if(warCards.get(0).rank < warCards.get(1).rank){              //player 2 won
                playerTwo.won(deckWon);
                playerOne.lost();
            }
            else if(warCards.get(0).rank > warCards.get(1).rank){         //player 1 won
                playerOne.won(deckWon);
                playerTwo.lost();
            }
            else {
                System.out.println(warCards.get(0).rank + " is equal to " + warCards.get(1).rank);
                warPlayers.addAll(players);
                war();
            }
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

        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());
    }

}
