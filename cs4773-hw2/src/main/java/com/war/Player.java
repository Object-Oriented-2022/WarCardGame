package com.war;
import java.util.*;


public class Player {
 //return queue
    public static ArrayList<Cards> deck = new ArrayList<>();

    public static ArrayList<Cards> tmpDeck = new ArrayList<>();

    public Player (ArrayList<Cards> list ){
        deck = list;

    }


    // create queue for each player for WAR
    public static Cards topCard(){
        Cards Cards = deck.get(0);
        return Cards;
    }
    public static void addCard(Cards card){
        deck.add(card);
    }
    public static void removeCard(Cards card){
        deck.remove(card);
    }
    public static void addToPointsPile(Cards card){
        tmpDeck.add(card);
    }
    public static int getPoints(){
        return (tmpDeck.size());
    }
}


