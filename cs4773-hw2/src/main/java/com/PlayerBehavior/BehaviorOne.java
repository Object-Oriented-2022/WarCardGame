package com.PlayerBehavior;

import com.card.Cards;

import java.util.ArrayList;

public class BehaviorOne extends Player{
    public BehaviorOne(ArrayList<Cards> deck, int number) {
        super(deck, number);
    }

    public void won(ArrayList<Cards> deckWon) {
        deck.addAll(deckWon);
        System.out.println("Player " + playerNum + " wins the round");
    }

    public void lost(){
        System.out.println("Player " + playerNum + " lost the round!");
    }

    @Override
    public int getPoints() {
        return deck.size();
    }
}
