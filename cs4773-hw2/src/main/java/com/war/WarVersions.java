package com.war;

import java.util.ArrayList;
import java.util.List;

import static com.war.DeckManipulation.halveDeck;


public class WarVersions {

    public static void warOne (int numRounds, ArrayList<Cards> deck) {
        List<ArrayList<Cards>> playerDecks = halveDeck(deck);
        Player playerOne = new Player(playerDecks.get(0));
        Player playerTwo= new Player(playerDecks.get(1));

        if(playerOne.getTopCard().rank < playerTwo.getTopCard().rank){
            System.out.println(playerOne.getTopCard().rank + " is less than " + playerTwo.getTopCard().rank);
        }
        else if(playerOne.getTopCard().rank > playerTwo.getTopCard().rank){
            System.out.println(playerOne.getTopCard().rank + " is greater than " + playerTwo.getTopCard().rank);
        }
        else if(playerOne.getTopCard().rank == playerTwo.getTopCard().rank){
            System.out.println(playerOne.getTopCard().rank + " is equal to " + playerTwo.getTopCard().rank);
        }


        System.out.println(playerOne.deck.toString());
        System.out.println(playerTwo.deck.toString());


    }
}
