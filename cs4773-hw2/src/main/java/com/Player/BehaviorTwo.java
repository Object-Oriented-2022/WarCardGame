package com.Player;

import com.card.Cards;

import java.util.ArrayList;

public class BehaviorTwo extends Player{
    public ArrayList<Cards> pointsWon;

    /**
     * Constructor for Player object type 2. Supers the deck and number to Player.java,
     * then initializes the points array.
     * @param deck array list of cards
     * @param number int player number
     */
    public BehaviorTwo(ArrayList<Cards> deck, int number){
        super(deck, number);
        pointsWon = new ArrayList<>();
    }

    /**
     * Adds deck to pointsWon and then prints player's current score.
     * @param deckWon array list of cards
     */
    public void won(ArrayList<Cards> deckWon){
        pointsWon.addAll(deckWon);
        System.out.println("Player " + playerNum + " wins the round");
        System.out.println("Player " + playerNum + " has a score of " + pointsWon.size());
    }

    /**
     * Prints player's current score.
     */
    public void lost(){
        System.out.println("Player " + playerNum + " has a score of " + pointsWon.size());
    }

    /**
     * Returns the size of the pointsWon array list.
     * @return int size
     */
    public int getPoints(){
        return pointsWon.size();
    }
}
