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

    public static List[] halveDeck(ArrayList<Cards> deck){
        List<Cards> firstHalf = deck.subList(0, 26);    //26
        List<Cards> secondHalf = deck.subList(26, 52);  //26

        return new List[] {firstHalf, secondHalf};
    }

    public static List[] thirdDeck(ArrayList<Cards> deck){
        List<Cards> oneThird = deck.subList(0, 17);     //17
        List<Cards> twoThird = deck.subList(17, 34);    //17
        List<Cards> threeThird = deck.subList(34, 52);  //18

        return new List[] {oneThird, twoThird, threeThird};
    }
}
