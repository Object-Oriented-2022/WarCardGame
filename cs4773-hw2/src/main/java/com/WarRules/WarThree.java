package com.WarRules;

import com.Player.Player;
import com.card.Cards;

import java.util.ArrayList;
import java.util.Arrays;

public class WarThree extends BaseRules{
    public static void WarThree (ArrayList<Player> players) {
        while(players.get(0).getDeckSize() > 0 && players.get(1).getDeckSize() > 0 && players.get(2).getDeckSize() > 0){
            resetRound();
            deckWon.clear();
            Cards onesCard = players.get(0).flipCards();
            Cards twosCard = players.get(1).flipCards();
            Cards threesCard = players.get(2).flipCards();
            ArrayList<Cards> cards = new ArrayList<>(Arrays.asList(onesCard, twosCard, threesCard));

            printCardsPulled(cards);

            ArrayList<Cards> deckWon = new ArrayList<>(Arrays.asList(onesCard, twosCard, threesCard));
            if(onesCard.rank < twosCard.rank && threesCard.rank < twosCard.rank){              //player 2 won
                players.get(1).won(deckWon);
                players.get(0).lost();
                players.get(2).lost();
            } else if(onesCard.rank > twosCard.rank && onesCard.rank > threesCard.rank){         //player 1 won
                players.get(0).won(deckWon);
                players.get(1).lost();
                players.get(2).lost();
            } else if (onesCard.rank < threesCard.rank && threesCard.rank > twosCard.rank){              //player 2 won
                players.get(2).won(deckWon);
                players.get(0).lost();
                players.get(1).lost();
            } else {
                System.out.println(onesCard.rank + " is equal to " + twosCard.rank + " is equal to " + threesCard.rank);
                //war(deckWon, new ArrayList<>(Arrays.asList(players.get(0), players.get(1), players.get(2))));
            }
            if(endCase != null)
                break;
        }

        //List<Integer> deckSizes = new ArrayList<>(Arrays.asList(players.get(0).getPoints(), players.get(1).getPoints(), players.get(2).getDeckSize()));
        deckSizes.add(players.get(0).getPoints());
        deckSizes.add(players.get(1).getPoints());
        deckSizes.add(players.get(2).getPoints());
        endGame();

        System.out.println(players.get(0).deck.toString());
        System.out.println(players.get(1).deck.toString());
        System.out.println(players.get(2).deck.toString());

    }

    //compareCards
}
