package com.card;

import java.util.ArrayList;

public class Cards {
    public Suit suit;
    public Value value;
    public int rank;

    public static ArrayList<Cards> cardDeck = new ArrayList<>();

    public Cards(Suit suit, Value value, int rank) {
        this.suit = suit;
        this.value = value;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return " plays " + value +
                " of " + suit;
    }

}
