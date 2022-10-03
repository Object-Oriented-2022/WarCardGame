package com.war;

import com.card.Cards;
import com.card.Suit;
import com.card.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.card.Cards.cardDeck;

/**
 * Holds methods to Shuffle the deck
 */
public class DeckManipulation {
    public static void shuffleDeck (ArrayList<Cards> deck, int seed) {
        Random random = new Random(seed);
        random.setSeed(seed);

        for (int i = 0; i < deck.size(); i++) {
            int elementPosition = random.nextInt(deck.size());
            Cards temp = deck.get(elementPosition);
            deck.set(elementPosition, deck.get(i));
            deck.set(i, temp);
        }
    }

    /**
     * Halves an array list into 2 array lists
     * @param deck array list
     * @return list of array lists
     */
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

    /**
     * Thirds an array list into 3 array lists, with one list containing 1 extra card.
     * @param deck array list
     * @return list of array lists
     */
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
                threeThird.add(deck.get(i));
            }
        }
        splitDecks.add(oneThird);
        splitDecks.add(twoThird);
        splitDecks.add(threeThird);
        return splitDecks;
    }

    /**
     * creates randomized array list of Cards objects and puts them into a deck of 52
     * @param seed int
     * @return array list of Cards objects
     */
    public static ArrayList<Cards> createDeck(int seed){
        for(int i =1; i < 15; i++){
            switch (i){
                case 1:
                    for (Value value:Value.values()
                    ){ cardDeck.add(new Cards(Suit.HEARTS, value, i++));
                    }
                    i = 1;
                case 15:
                    for (Value value:Value.values()
                    ){ cardDeck.add(new Cards(Suit.DIAMONDS, value, i++));
                    }
                    i = 1;
                case 30:
                    for (Value value:Value.values()
                    ){ cardDeck.add(new Cards(Suit.SPADES, value, i++));
                    }
                    i = 1;
                case 45:
                    for (Value value:Value.values()
                    ){ cardDeck.add(new Cards(Suit.CLUBS, value, i++));
                    }
                    i = 1;
            }
        }
        shuffleDeck(cardDeck, seed);
        return cardDeck;
    }

}
