package com.WarRules;

import com.Player.Player;
import com.war.EndCases;

import java.util.ArrayList;
import java.util.Arrays;

public class WarOne extends BaseRules{


    public static void WarOne (int maxRounds, Player playerOne, Player playerTwo) {
        int currentRound = 0;
        players = new ArrayList<>(Arrays.asList(playerOne, playerTwo));
        while(currentRound < maxRounds && !emptyDeckCheck()){
            resetRound();
            warCards = pullCards(players);
            printCardsPulled(warCards);
            deckWon.addAll(warCards);
            //set winner
            compareCards();
/*
            List<Integer> ranks = new ArrayList<>();
            warCards.forEach(card -> ranks.add(card.rank));
            int highestCard = Collections.max(ranks);
            int frequency = Collections.frequency(ranks, highestCard);
            if(Collections.frequency(ranks, highestCard) == 1) {
                winner = players.get(ranks.indexOf(highestCard));
            }
            else{

                warPlayers.add(players.get(ranks.indexOf(highestCard)));
                ranks.remove(highestCard);
                warPlayers.add()
            }
                */
            // warCards.get(ranks.indexOf(highestCard));

            /*if(warCards.get(0).rank < warCards.get(1).rank){              //player 2 won
                playerTwo.won(deckWon);
            }
            else if(warCards.get(0).rank > warCards.get(1).rank){         //player 1 won
                playerOne.won(deckWon);
            }
            else {
                warPlayers.addAll(players);
                war();
            }*/
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
        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());
        findWinner();
        endGame();

    }

    private static void compareCards() {
        if(warCards.get(0).rank < warCards.get(1).rank){              //player 2 won
            players.get(1).won(deckWon);
        }
        else if(warCards.get(0).rank > warCards.get(1).rank){         //player 1 won
            players.get(0).won(deckWon);
        }
        else {
            warPlayers.addAll(players);
            war();
        }
    }


}
