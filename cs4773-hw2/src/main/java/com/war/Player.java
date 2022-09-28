package com.war;
import java.util.ArrayList;

class Player{
    public ArrayList<Cards> deck;
    public ArrayList<Cards> pointsWon;
    public Player (ArrayList<Cards> deck){
        this.deck = deck;
        pointsWon = new ArrayList<>();
    }

    public Cards getTopCard(){
        return this.deck.get(0);
    }

    public void putCardBack(Cards card){
        deck.add(card);
    }

    public void removeCard(Cards card){
        deck.remove(card);
    }

    public void addToPointsDeck(Cards card){
        pointsWon.add(card);
    }

    public int getPoints(){
        return pointsWon.size();
    }
}

/*
class Player {
 //return queue
    public static ArrayList<Cards> deck = new ArrayList<>();
    public static ArrayList<Cards> pointsWonDeck = new ArrayList<>();

    public Player (ArrayList<Cards> cards){
        this.deck = deck;
    }
    // create queue for each player for WAR
    public static Cards topCard(){
        return deck.get(0);
    }
    public static void addCard(Cards card){
        deck.add(card);
    }
    public static void removeCard(Cards card){
        deck.remove(card);
    }
    public static void addToPointsPile(Cards card){
        pointsWonDeck.add(card);
    }
    public static int getPoints(){
        return (pointsWonDeck.size());
    }
}
*/

