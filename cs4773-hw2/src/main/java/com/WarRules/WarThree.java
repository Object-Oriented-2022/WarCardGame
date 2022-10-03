package com.WarRules;

import com.Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Subclass to BaseRules, utilizes Super's methods to play a game of war, version 3.
 */
public class WarThree extends BaseRules{

    /**
     * Starts war card game version 3. This version has 3 players and passes cards won
     * to a separate points deck. Returns nothing. Implements methods from Base
     * Rules Class.
     * @param playerOne Player obj
     * @param playerTwo Player obj
     * @param playerThree Player obj
     */
    public static void warThree (Player playerOne, Player playerTwo, Player playerThree) {
        players = new ArrayList<>(Arrays.asList(playerOne, playerTwo, playerThree));
        viablePlayers.addAll(players);
        while(emptyDeckCheck()){
            beginRound();
            compareCards();
            if(endCase != null)
                break;
        }

        deckSizes.add(players.get(0).getPoints());
        deckSizes.add(players.get(1).getPoints());
        deckSizes.add(players.get(2).getPoints());
        findWinner();
        endGame();
    }
    private static void compareCards(){
        List<Integer> ranks = new ArrayList<>();
        warCards.forEach(card -> ranks.add(card.rank));
        int highestCard = Collections.max(ranks);
        int frequency = Collections.frequency(ranks, highestCard);
        if(frequency == 1) {
            viablePlayers.get(ranks.indexOf(highestCard)).won(deckWon);
            for(int i = 0; i < viablePlayers.size(); i++){
                if(ranks.indexOf(highestCard) != i)
                    viablePlayers.get(i).lost();
            }
        } else if (frequency == 2) {
            warPlayers.add(viablePlayers.get(ranks.indexOf(highestCard)));
            ranks.remove((Integer) highestCard);
            warPlayers.add(viablePlayers.get(ranks.indexOf(highestCard)));
            war();
        } else if (frequency == 3){
            warPlayers.addAll(viablePlayers);
            war();
        }
    }
}
