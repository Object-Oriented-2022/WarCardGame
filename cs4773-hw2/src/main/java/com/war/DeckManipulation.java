package com.war;

import com.card.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckManipulation {
    public static void shuffleDeck (ArrayList<Cards> deck, int seed) {
        //do we need to take into account no seed?
        Random random = new Random(seed);
        random.setSeed(seed);

        for (int i = 0; i < deck.size(); i++) {
            int elementPosition = random.nextInt(deck.size());
            Cards temp = deck.get(elementPosition);
            deck.set(elementPosition, deck.get(i));
            deck.set(i, temp);
        }
    }

    public static List<ArrayList<Cards>> halveDeck(ArrayList<Cards> deck){
        List<ArrayList<Cards>> splitDecks = new ArrayList<>();
        ArrayList<Cards> firstHalf = new ArrayList<>();
        ArrayList<Cards> secondHalf = new ArrayList<>();

        for(int i = 0; i < 52; i++){
            if( i < 26 ){
                firstHalf.add(deck.get(i));
            }
            if( i >= 26 ) {
                secondHalf.add(deck.get(i));
            }
        }
        splitDecks.add(firstHalf);
        splitDecks.add(secondHalf);
        return splitDecks;
    }

    public static List<ArrayList<Cards>> thirdDeck(ArrayList<Cards> deck){
        List<ArrayList<Cards>> splitDecks = new ArrayList<>();
        ArrayList<Cards> oneThird = new ArrayList<>();
        ArrayList<Cards> twoThird = new ArrayList<>();
        ArrayList<Cards> threeThird = new ArrayList<>();

        for(int i = 0; i < 52; i++){
            if( i < 17 ){
                oneThird.add(deck.get(i));
            }
            if( i >= 17 && i < 35 ) {
                twoThird.add(deck.get(i));
            }
            if( i >= 35 ) {
                twoThird.add(deck.get(i));
            }
        }
        splitDecks.add(oneThird);
        splitDecks.add(twoThird);
        splitDecks.add(threeThird);
        return splitDecks;
    }




}
