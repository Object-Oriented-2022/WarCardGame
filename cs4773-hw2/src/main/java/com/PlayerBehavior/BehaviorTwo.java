package com.PlayerBehavior;

import com.card.Cards;

import java.util.ArrayList;

public class BehaviorTwo extends Player{
    public ArrayList<Cards> pointsWon;

    public BehaviorTwo(ArrayList<Cards> deck, int number){
        super(deck, number);
        pointsWon = new ArrayList<>();
    }
    public void won(ArrayList<Cards> deckWon){
        pointsWon.addAll(deckWon);
        System.out.println("Player " + playerNum + " wins the round");
        System.out.println("Player " + playerNum + " has a score of " + pointsWon.size());
        //System.out.println(pointsWon.toString());
    }
    public void lost(){
        System.out.println("Player " + playerNum + " has a score of " + pointsWon.size());
        //System.out.println(pointsWon.toString());
    }
    public int getPoints(){
        return pointsWon.size();
    }
}
