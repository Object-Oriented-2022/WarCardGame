package com.Player;

import com.card.Cards;

import java.util.ArrayList;

/**
 * Abstract player behaviors that every player should have.
 * @author Theresa Crawford
 */
public interface PlayerBehavior {
    Cards getTopCard();
    Cards flipCards();
    void addCard(Cards card);
    void removeCard(Cards card);
    void won(ArrayList<Cards> deckWon);
    void lost();
    int getDeckSize();
    int getPoints();

}
