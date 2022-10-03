package com.card;

import java.util.ArrayList;

/**
 * Cards object class that takes in Suit and Value enumeration to create a "card"
 */
public class Cards {
    public Suit suit;
    public Value value;
    public int rank;

    public static ArrayList<Cards> cardDeck = new ArrayList<>();

    /**
     * Constructor for Cards object.
     * @param suit Suit enum
     * @param value Value enum
     * @param rank int number for comparisons
     */
    public Cards(Suit suit, Value value, int rank) {
        this.suit = suit;
        this.value = value;
        this.rank = rank;
    }

    /**
     * Override toString to print a card object out like so:
     *  plays THREE of SPADES
     * @return string
     */
    @Override
    public String toString() {
        return " plays " + value +
                " of " + suit;
    }

}
