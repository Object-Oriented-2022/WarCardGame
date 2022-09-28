package com.war;

import java.util.ArrayList;
import java.util.List;

import static com.war.DeckManipulation.halveDeck;

public class WarVersions {

    public static void warOne (int numRounds, ArrayList<Cards> deck) {
        List<Cards> [] splitDeck = halveDeck(deck);
        //queue splitDeck[0]
        //queue splitDeck[1]
        //war game itself
        System.out.println(splitDeck[0]);
        System.out.println(splitDeck[1]);
    }
}
