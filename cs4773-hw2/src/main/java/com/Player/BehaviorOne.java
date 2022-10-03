package com.Player;

import com.card.Cards;

import java.util.ArrayList;

public class BehaviorOne extends Player{

    /**
     * Constructor for Player object type 1. Supers the deck and number.
     * @param deck array list of Cards
     * @param number int
     */
    public BehaviorOne(ArrayList<Cards> deck, int number) {
        super(deck, number);
    }

    /**
     * Recycles(Adds) deckWon back to player deck.
     * @param deckWon array list of Cards
     */
    public void won(ArrayList<Cards> deckWon) {
        deck.addAll(deckWon);
        System.out.println("Player " + playerNum + " wins the round");
    }

    /**
     * Prints out that the player lost the round for version 1
     */
    public void lost(){
        System.out.println("Player " + playerNum + " lost the round!");
    }

    /**
     * returns the size of the deck array as the number of points a player has.
     * @return int size of deck
     */
    @Override
    public int getPoints() {
        return deck.size();
    }
}
