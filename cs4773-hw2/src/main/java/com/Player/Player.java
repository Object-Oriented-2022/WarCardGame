package com.Player;
import com.card.Cards;

import java.util.ArrayList;

/**
 * Player class that implements the PlayerBehavior interface. Only holds a
 * constructor, deck arraylist, and player number. Only 5 of the 8 interface
 * classes are created here because they are uniform across all war versions.
 */
public abstract class Player implements PlayerBehavior {
    public int playerNum;
    public ArrayList<Cards> deck;

    /**
     * Constructor class for Player objects
     * @param deck of Cards
     * @param playerNum aka Player's Number
     */
    public Player(ArrayList<Cards> deck, int playerNum){
        this.playerNum = playerNum;
        this.deck = deck;
    }

    /**
     * Returns the top card in the playing deck
     * @return Cards object
     */
    public Cards getTopCard(){
        return this.deck.get(0);
    }

    /**
     * Grabs top card and removes it from the deck
     * @return Cards obj
     */
    public Cards flipCards(){
        Cards topCard = getTopCard();
        removeCard(topCard);
        return topCard;
    }

    /**
     * removes a given card from the deck
     * @param card obj
     */
    public void removeCard(Cards card){
        deck.remove(card);
    }

    /**
     * adds a given card to the back of the deck
     * @param card obj
     */
    public void addCard(Cards card){
        deck.add(card);
    }


    /**
     * returns the size of the playing deck
     * @return int
     */
    public int getDeckSize(){
        return deck.size();
    }

}
