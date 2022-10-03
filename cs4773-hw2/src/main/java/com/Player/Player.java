package com.Player;
import com.card.Cards;

import java.util.ArrayList;

public abstract class Player implements PlayerBehavior {
    public int playerNum;
    public ArrayList<Cards> deck;
    //public ArrayList<Cards> pointsWon;
    public Player(ArrayList<Cards> deck, int playerNum){
        this.playerNum = playerNum;
        this.deck = deck;
    }

    public Cards getTopCard(){
        return this.deck.get(0);
    }

    public Cards flipCards(){
        Cards topCard = getTopCard();
        removeCard(topCard);
        return topCard;
    }

    public void addCard(Cards card){
        deck.add(card);
    }

    public void removeCard(Cards card){
        deck.remove(card);
    }

    public int getDeckSize(){
        return deck.size();
    }

}
