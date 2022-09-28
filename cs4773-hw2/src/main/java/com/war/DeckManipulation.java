package com.war;

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

    /*public static List<ArrayList>[] thirdDeck(ArrayList<Cards> deck){
        List<Cards> oneThird = deck.subList(0, 17);     //17
        List<Cards> twoThird = deck.subList(17, 34);    //17
        List<Cards> threeThird = deck.subList(34, 52);  //18

        return new List[] {oneThird, twoThird, threeThird};
    }*/
}
