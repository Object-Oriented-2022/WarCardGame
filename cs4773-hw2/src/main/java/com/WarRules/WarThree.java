package com.WarRules;

import com.Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WarThree extends BaseRules{
    public static void WarThree (Player playerOne, Player playerTwo, Player playerThree) {
        players = new ArrayList<>(Arrays.asList(playerOne, playerTwo, playerThree));
        while(!emptyDeckCheck()){
            //beginRound();
            resetRound();
            warCards = pullCards(players);
            printCardsPulled(warCards);
            deckWon.addAll(warCards);

            //ArrayList<Cards> cards = new ArrayList<>(Arrays.asList(onesCard, twosCard, threesCard));


            //ArrayList<Cards> deckWon = new ArrayList<>(Arrays.asList(onesCard, twosCard, threesCard));
            compareCards();
            if(endCase != null)
                break;
        }

        //List<Integer> deckSizes = new ArrayList<>(Arrays.asList(players.get(0).getPoints(), players.get(1).getPoints(), players.get(2).getDeckSize()));
        deckSizes.add(players.get(0).getPoints());
        deckSizes.add(players.get(1).getPoints());
        deckSizes.add(players.get(2).getPoints());
        findWinner();
        endGame();

        System.out.println(players.get(0).deck.toString());
        System.out.println(players.get(1).deck.toString());
        System.out.println(players.get(2).deck.toString());

    }

    private static void compareCards(){

        List<Integer> ranks = new ArrayList<>();
        warCards.forEach(card -> ranks.add(card.rank));
        int highestCard = Collections.max(ranks);
        int frequency = Collections.frequency(ranks, highestCard);
        if(frequency == 1) {
            players.get(ranks.indexOf(highestCard)).won(deckWon);
            for(int i = 0; i < players.size(); i++){
                if(ranks.indexOf(highestCard) != i)
                    players.get(i).lost();
            }
        } else if (frequency == 2) {
            warPlayers.add(players.get(ranks.indexOf(highestCard)));
            ranks.remove(ranks.indexOf(highestCard));
            warPlayers.add(players.get(ranks.indexOf(highestCard)));
            war();
        } else if (frequency == 3){
            warPlayers.addAll(players);
            war();
        }
        /*
        //
        if(ranks.get(0) < ranks.get(1) && ranks.get(2) < ranks.get(1)){              //player 2 won
            players.get(1).won(deckWon);
            players.get(0).lost();
            players.get(2).lost();
        } else if(ranks.get(0) > ranks.get(1) && ranks.get(0) > ranks.get(2)){         //player 1 won
            players.get(0).won(deckWon);
            players.get(1).lost();
            players.get(2).lost();
        } else if (ranks.get(0) < ranks.get(2) && ranks.get(2) > ranks.get(1)){              //player 3 won
            players.get(2).won(deckWon);
            players.get(0).lost();
            players.get(1).lost();
        } else {
            System.out.println(ranks.get(0) + " is equal to " + ranks.get(1) + " is equal to " + ranks.get(2));
            //war(deckWon, new ArrayList<>(Arrays.asList(players.get(0), players.get(1), players.get(2))));
        }*/
    }
}
